package com.semokin.app.application.service;

import com.semokin.app.adapter.config.security.JwtUtil;
import com.semokin.app.adapter.dto.request.LoginRequest;
import com.semokin.app.adapter.dto.request.RegisterCustomerRequest;
import com.semokin.app.adapter.dto.response.LoginResponse;
import com.semokin.app.adapter.dto.response.RegisterResponse;
import com.semokin.app.application.contract.AuthService;
import com.semokin.app.application.contract.CustomerService;
import com.semokin.app.application.contract.RoleService;
import com.semokin.app.application.contract.ValidateService;
import com.semokin.app.domain.model.AppUser;
import com.semokin.app.domain.model.Role;
import com.semokin.app.domain.model.User;
import com.semokin.app.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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
    @Override
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
                .build();
        userRepository.save(newUser);
//        customer service create
        customerService.createCustomer(newUser, customerRequest.getCustomerCreateRequest());
        return RegisterResponse.builder()
                .email(newUser.getEmail())
                .role(List.of(role.getRole()))
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        validateService.validate(loginRequest);

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
}
