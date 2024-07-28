package com.semokin.app.application.service;

import com.semokin.app.application.contract.UserService;
import com.semokin.app.domain.model.AppUser;
import com.semokin.app.domain.model.User;
import com.semokin.app.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findFirstByEmailOrUsername(username,username)
                .orElseThrow(()-> new UsernameNotFoundException("Username or email not found"));

        return AppUser.builder()
                .Id(user.getId())
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();
    }

    @Override
    public AppUser loadUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new UsernameNotFoundException("Username or email not found"));
        return AppUser.builder()
                .Id(user.getId())
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();
    }
}
