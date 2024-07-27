package com.semokin.app.application.contract;

import com.semokin.app.domain.model.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    AppUser loadUserById(String id);
}
