package com.pranav.blog.backend.service;

import com.pranav.blog.backend.dto.UserProfileResponse;
import com.pranav.blog.backend.entity.User;
import com.pranav.blog.backend.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    public UserProfileResponse getCurrentUser(
            Authentication authentication
    ) {

        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        return new UserProfileResponse(
                user.getUsername(),
                user.getEmail(),
                user.getRole().getName()
        );
    }
}