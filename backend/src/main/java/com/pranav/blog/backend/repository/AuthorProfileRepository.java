package com.pranav.blog.backend.repository;

import com.pranav.blog.backend.entity.AuthorProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorProfileRepository
        extends JpaRepository<AuthorProfile, Long> {
}