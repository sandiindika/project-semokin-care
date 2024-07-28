package com.semokin.app.application.service;

import com.semokin.app.adapter.dto.request.CustomerCreateRequest;
import com.semokin.app.adapter.dto.request.RegisterCustomerRequest;
import com.semokin.app.application.contract.AuthService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
public class AuthServiceTest {
    @Autowired
    private final AuthService authService;
    // test cases for AuthService

    @Test
    void registerSuccess() {
        RegisterCustomerRequest registerCustomerRequest = RegisterCustomerRequest.builder()
                .email("test@example.com")
                .username("testuser")
                .password("test123")
                .customerCreateRequest(CustomerCreateRequest.builder()
                        .firstName("John")
                        .lastName("Doe")
                        .phoneNumber("+1234567890")
                        .dateOfBirth(12L)
                        .build())
                .build();
    }
}
