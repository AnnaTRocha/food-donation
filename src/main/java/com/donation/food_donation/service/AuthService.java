package com.donation.food_donation.service;

import com.donation.food_donation.dto.AuthResponse;
import com.donation.food_donation.dto.LoginRequest;
import com.donation.food_donation.dto.RegisterRequest;
import com.donation.food_donation.exception.EmailAlreadyExistsException;
import com.donation.food_donation.model.User;
import com.donation.food_donation.repository.UserRepository;
import com.donation.food_donation.security.CustomUserDetailsService;
import com.donation.food_donation.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.donation.food_donation.dto.LoginResponse;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService userDetailsService; // Adicione esta injeção

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already in use");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setType(request.getType());
        user.setOrganizationName(request.getOrganizationName());

        userRepository.save(user);
        String token = jwtTokenProvider.generateToken(user.getEmail()); // Gera token com email

        return new AuthResponse(token, user.getEmail(), user.getName(), user.getType());
    }

    public LoginResponse login(LoginRequest request) {
        // Autenticação
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Obtém o usuário completo
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Gera token com o email
        String token = jwtTokenProvider.generateToken(user.getEmail());

        // Retorna LoginResponse com todos os campos necessários
        return new LoginResponse(
                token,
                user.getEmail(),
                user.getName(),
                user.getType()
        );
    }

    public LoginResponse createLoginResponse(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtTokenProvider.generateToken(email);

        return LoginResponse.fromUserDetails(
                token,
                user.getEmail(),
                user.getName(),
                user.getType()
        );
    }

}