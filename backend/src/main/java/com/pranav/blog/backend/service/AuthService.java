package com.pranav.blog.backend.service;

import com.pranav.blog.backend.dto.LoginRequest;
import com.pranav.blog.backend.dto.LoginResponse;
import com.pranav.blog.backend.dto.auth.RefreshTokenRequest;
import com.pranav.blog.backend.dto.auth.RefreshTokenResponse;
import com.pranav.blog.backend.entity.RefreshToken;
import com.pranav.blog.backend.repository.UserRepository;
import com.pranav.blog.backend.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import com.pranav.blog.backend.constants.ApiMessages;
import com.pranav.blog.backend.entity.User;
import com.pranav.blog.backend.exception.ResourceNotFoundException;
import com.pranav.blog.backend.repository.UserRepository;
@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RefreshTokenService refreshTokenService;
    public AuthService(
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            UserRepository userRepository,
            RefreshTokenService refreshTokenService
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.refreshTokenService = refreshTokenService;
    }
    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        String accessToken = jwtService.generateToken(user.getUsername());

        RefreshToken refreshToken =
                refreshTokenService.createRefreshToken(user);

        return new LoginResponse(
                accessToken,
                refreshToken.getToken(),
                "Bearer",
                jwtService.getExpiration(),
                user.getUsername(),
                user.getRole().getName()
        );
    }
    public RefreshTokenResponse refreshToken(
            RefreshTokenRequest request
    ) {

        User user = refreshTokenService.validateRefreshToken(
                request.getRefreshToken()
        );

        String accessToken =
                jwtService.generateToken(user.getUsername());

        return new RefreshTokenResponse(
                accessToken,
                jwtService.getExpiration()
        );
    }
}