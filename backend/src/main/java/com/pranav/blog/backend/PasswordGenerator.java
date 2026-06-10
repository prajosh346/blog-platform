package com.pranav.blog.backend;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder =
                new BCryptPasswordEncoder();

        String password = "Sanjay@123";

        String hash = encoder.encode(password);

        System.out.println("HASH = " + hash);

        System.out.println(
                "MATCHES = "
                        + encoder.matches(password, hash)
        );
    }
}