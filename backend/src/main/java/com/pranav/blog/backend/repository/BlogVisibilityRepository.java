package com.pranav.blog.backend.repository;

import com.pranav.blog.backend.entity.BlogVisibility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogVisibilityRepository
        extends JpaRepository<BlogVisibility, Long> {
}