package com.semokin.app.adapter.dto.response;

import com.semokin.app.domain.model.Role;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class LoginResponse {
    private String token;
    private String email;
    private Set<Role> roles;
}
