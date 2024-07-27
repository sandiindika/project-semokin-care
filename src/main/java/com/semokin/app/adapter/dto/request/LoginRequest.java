package com.semokin.app.adapter.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = "Email or Username can't be empty")
    private String emailOrUsername;
    @NotBlank(message = "Password can't be empty")
    private String password;
}
