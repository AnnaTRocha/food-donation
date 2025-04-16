package com.donation.food_donation.controller;

import com.donation.food_donation.dto.AuthResponse;
import com.donation.food_donation.dto.LoginRequest;
import com.donation.food_donation.dto.LoginResponse;
import com.donation.food_donation.dto.RegisterRequest;
import com.donation.food_donation.security.JwtTokenProvider;
import com.donation.food_donation.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.donation.food_donation.dto.BasicLoginResponse;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        // Verifique se authService.login não está causando recursão
        return ResponseEntity.ok(authService.login(request));
    }
}