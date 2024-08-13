package com.semokin.app.adapter.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Long dateOfBirth;
    private String address;
    private Long salary;
}
