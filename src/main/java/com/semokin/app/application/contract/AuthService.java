package com.semokin.app.application.contract;

import com.semokin.app.adapter.dto.request.LoginRequest;
import com.semokin.app.adapter.dto.request.RegisterCustomerRequest;
import com.semokin.app.adapter.dto.response.LoginResponse;
import com.semokin.app.adapter.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterCustomerRequest customerRequest);
    LoginResponse login(LoginRequest loginRequest);
}
