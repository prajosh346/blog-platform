package com.pranav.blog.backend.controller;

import com.pranav.blog.backend.dto.LoginRequest;
import com.pranav.blog.backend.dto.LoginResponse;
import com.pranav.blog.backend.dto.auth.RefreshTokenRequest;
import com.pranav.blog.backend.dto.auth.RefreshTokenResponse;
import com.pranav.blog.backend.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(
            AuthService authService
    ) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request
    ) {
        return authService.login(request);
    }

    @PostMapping("/refresh")
    public RefreshTokenResponse refreshToken(
            @RequestBody RefreshTokenRequest request
    ) {
        return authService.refreshToken(request);
    }
}