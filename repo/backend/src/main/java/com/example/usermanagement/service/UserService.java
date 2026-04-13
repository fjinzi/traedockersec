package com.example.usermanagement.service;

import com.example.usermanagement.dto.LoginRequest;
import com.example.usermanagement.dto.LoginResponse;

public interface UserService {
    LoginResponse login(LoginRequest request);
}
