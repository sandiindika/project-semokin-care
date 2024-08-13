package com.semokin.app.application.service;

import com.semokin.app.adapter.config.security.JwtUtil;
import com.semokin.app.adapter.dto.request.CustomerCreateRequest;
import com.semokin.app.adapter.dto.request.LoginRequest;
import com.semokin.app.adapter.dto.request.RegisterCustomerRequest;
import com.semokin.app.adapter.dto.request.RegisterStaffRequest;
import com.semokin.app.adapter.dto.response.LoginResponse;
import com.semokin.app.adapter.dto.response.RegisterResponse;
import com.semokin.app.application.contract.*;
import com.semokin.app.domain.model.AppUser;
import com.semokin.app.domain.model.Role;
import com.semokin.app.domain.model.User;
import com.semokin.app.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final CustomerService customerService;
    private final RoleService roleService;
    private final ValidateService validateService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final StaffService staffService;

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;
    @Value("${spring.mail.url.activate}")
    private String urlEmail;

    @Override
    @Transactional
    public RegisterResponse register(RegisterCustomerRequest customerRequest) {
        validateService.validate(customerRequest);

        Optional<User> user = userRepository.findFirstByEmailOrUsername(customerRequest.getUsername(), customerRequest.getEmail());
        if (user.isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Email or Username already exists");
        }
        Role role = roleService.getOrSave(Role.Privilege.CUSTOMER);
        User newUser = User.builder()
                .email(customerRequest.getEmail())
                .username(customerRequest.getUsername())
                .password(passwordEncoder.encode(customerRequest.getPassword()))
                .roles(Set.of(role))
                .isActive(false)
                .build();
        userRepository.save(newUser);
//        customer service create
        customerService.createCustomer(newUser, customerRequest.getCustomerCreateRequest());

//        send email
        if (!sendEmail(newUser.getEmail())){
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Invalid email");
        }
        return RegisterResponse.builder()
                .email(newUser.getEmail())
                .role(List.of(role.getRole()))
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        validateService.validate(loginRequest);

        Optional<User> user = userRepository.findFirstByEmailOrUsername(loginRequest.getEmailOrUsername(), loginRequest.getEmailOrUsername());
        if (user.isPresent()){
            if (!user.get().isActive()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user is not active");
            }
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmailOrUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser appUser = (AppUser) authentication.getPrincipal();
        String token = jwtUtil.generateToken(appUser);

        return LoginResponse.builder()
                .email(loginRequest.getEmailOrUsername())
                .token(token)
                .roles(appUser.getRoles())
                .build();
    }

    @Transactional
    public void activate(String id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        if (user.getCreateActiveToken() > System.currentTimeMillis()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Active Token is expired");
        }
        user.setActive(true);;
    }

    @Transactional
    public boolean sendEmail(String email){
        User user = userRepository.findFirstByEmailOrUsername(email,email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        String id = user.getId();
        long currentTime = System.currentTimeMillis();
        user.setCreateActiveToken(currentTime);
        userRepository.save(user);
        SimpleMailMessage simpleMailMessage = getSimpleMailMessage(email,id);

        javaMailSender.send(simpleMailMessage);
        return true;
    }

    private SimpleMailMessage getSimpleMailMessage(String email,String id) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(senderEmail);
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Activate email");
        simpleMailMessage.setText(
                """
                        <!DOCTYPE html>
                        <html>
                        <body>
                        <h1>Activate Link</h1>
               """+"  <p><a href="+urlEmail+"?id="+id+">Link activate</a></p>\n" +
                        "</body>\n" +
                        "</html>"
        );
        return simpleMailMessage;
    }

    @Transactional
//    register admin
    public RegisterResponse registerAdmin(RegisterStaffRequest request) {
        validateService.validate(request);

        Optional<User> user = userRepository.findFirstByEmailOrUsername(request.getUsername(), request.getEmail());
        if (user.isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Email or Username already exists");
        }
        Role role = roleService.getOrSave(Role.Privilege.STAFF_ADMIN);
        User newUser = User.builder()
               .email(request.getEmail())
               .username(request.getUsername())
               .password(passwordEncoder.encode(request.getPassword()))
               .roles(Set.of(role))
               .isActive(false)
               .build();
        userRepository.save(newUser);

//        staff create
        staffService.createStaff(newUser, request.getStaffCreateRequest());
        if (!sendEmail(newUser.getEmail())){
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Invalid email");
        }
        return RegisterResponse.builder()
               .email(newUser.getEmail())
               .role(List.of(role.getRole()))
               .build();
    }

}
