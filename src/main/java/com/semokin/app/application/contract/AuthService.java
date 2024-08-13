package com.semokin.app.application.contract;

import com.semokin.app.adapter.dto.request.LoginRequest;
import com.semokin.app.adapter.dto.request.RegisterCustomerRequest;
import com.semokin.app.adapter.dto.request.RegisterStaffRequest;
import com.semokin.app.adapter.dto.response.LoginResponse;
import com.semokin.app.adapter.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterCustomerRequest customerRequest); // todo: POST /auth/register
    LoginResponse login(LoginRequest loginRequest); // todo: POST /auth/login
    void activate(String id); // todo: GET /auth/activate?id=userId
    boolean sendEmail(String email); // todo: POST /auth/sendEmail
    RegisterResponse registerAdmin(RegisterStaffRequest request); // todo: Post /auth/register/admin
}
