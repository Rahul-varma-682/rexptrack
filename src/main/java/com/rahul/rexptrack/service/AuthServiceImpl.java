package com.rahul.rexptrack.service;

import com.rahul.rexptrack.dto.request.LoginRequest;
import com.rahul.rexptrack.dto.request.RegisterRequest;
import com.rahul.rexptrack.dto.response.AuthResponse;
import com.rahul.rexptrack.dto.response.UserResponse;
import com.rahul.rexptrack.exception.ResourceNotFoundException;
import com.rahul.rexptrack.model.User;
import com.rahul.rexptrack.repository.UserRepository;
import com.rahul.rexptrack.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("An account already exists for this email address");
        }
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());
        user.setCurrency(request.getCurrency());
        return toAuthResponse(userRepository.save(user));
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User", null));
        return toAuthResponse(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getCurrentUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", null));
        UserResponse response = new UserResponse();
        response.setId(user.getId()); response.setName(user.getName()); response.setEmail(user.getEmail());
        response.setPhone(user.getPhone()); response.setCurrency(user.getCurrency()); response.setMonthlyIncome(user.getMonthlyIncome());
        response.setRole(user.getRole().name()); response.setIsPremium(user.isPremium()); response.setCreatedAt(user.getCreatedAt());
        return response;
    }

    private AuthResponse toAuthResponse(User user) {
        AuthResponse response = new AuthResponse();
        response.setToken(jwtService.generateToken(user)); response.setId(user.getId()); response.setName(user.getName());
        response.setEmail(user.getEmail()); response.setRole(user.getRole().name()); response.setIsPremium(user.isPremium());
        return response;
    }

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }
}

