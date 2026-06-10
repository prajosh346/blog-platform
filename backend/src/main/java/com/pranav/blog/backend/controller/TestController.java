package com.pranav.blog.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/super-admin/test")
    public String superAdminTest() {
        return "SUPER_ADMIN API Working";
    }

    @GetMapping("/api/admin/test")
    public String adminTest() {
        return "ADMIN API Working";
    }

    @GetMapping("/api/author/test")
    public String authorTest() {
        return "AUTHOR API Working";
    }
}