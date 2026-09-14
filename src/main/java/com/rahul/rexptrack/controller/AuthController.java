package com.rahul.rexptrack.controller;

import com.rahul.rexptrack.dto.request.LoginRequest;
import com.rahul.rexptrack.dto.request.RegisterRequest;
import com.rahul.rexptrack.dto.response.ApiResponse;
import com.rahul.rexptrack.dto.response.AuthResponse;
import com.rahul.rexptrack.dto.response.UserResponse;
import com.rahul.rexptrack.service.AuthService;
import com.rahul.rexptrack.util.SecurityUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    private final SecurityUtil securityUtil;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Registration successful", authService.register(request)));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Login successful", authService.login(request)));
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserResponse>> currentUser() {
        return ResponseEntity.ok(ApiResponse.success("Current user retrieved", authService.getCurrentUser(securityUtil.getCurrentUserEmail())));
    }

    public AuthController(AuthService authService, SecurityUtil securityUtil) {
        this.authService = authService;
        this.securityUtil = securityUtil;
    }
}


