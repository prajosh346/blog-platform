package com.pranav.blog.backend.repository;

import com.pranav.blog.backend.entity.BlogTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogTagRepository
        extends JpaRepository<BlogTag, Long> {
}