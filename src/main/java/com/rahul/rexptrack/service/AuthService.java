package com.rahul.rexptrack.service;

import com.rahul.rexptrack.dto.request.LoginRequest;
import com.rahul.rexptrack.dto.request.RegisterRequest;
import com.rahul.rexptrack.dto.response.AuthResponse;
import com.rahul.rexptrack.dto.response.UserResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
    UserResponse getCurrentUser(String email);
}
