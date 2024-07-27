package com.semokin.app.adapter.dto.response;

import com.semokin.app.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private String username;
    private String email;
    private List<Role.Privilege> role;
}
