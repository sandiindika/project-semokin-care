package com.semokin.app.adapter.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterStaffRequest {
    @NotBlank(message = "Username can't be empty")
    private String username;
    @NotBlank(message = "Email can't be empty")
    private String email;
    @NotBlank(message = "Password can't be empty")
    private String password;
    @NotNull(message = "can't be null")
    private StaffCreateRequest staffCreateRequest;
}
