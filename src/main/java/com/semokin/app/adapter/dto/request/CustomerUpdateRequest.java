package com.semokin.app.adapter.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class CustomerUpdateRequest {
    @NotNull(message = "ID can't be null")
    private String id;
    @NotBlank(message = "Name can't be empty")
    private String firstName;
    private String lastName;
    @Size(min = 10, max = 15)
    @Pattern(regexp = "\\+?[0-9]+", message = "Phone number is not valid")
    private String phoneNumber;

    @NotBlank(message = "Address can't be empty")
    private String address;

    private Long dateOfBirth;

}
