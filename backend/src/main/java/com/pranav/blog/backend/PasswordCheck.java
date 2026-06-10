package com.pranav.blog.backend;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordCheck {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String hash = "$2a$10$ZqhlvwLdCxYuFwtbmx2rjeSphr9I0xhG4RfcKMdTFDMiMRWSeoMvG";

        System.out.println(
                encoder.matches(
                        "Ketaki@123",
                        hash
                )
        );
    }
}