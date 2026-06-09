package com.pranav.blog.backend.repository;

import com.pranav.blog.backend.entity.MetaTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetaTagRepository
        extends JpaRepository<MetaTag, Long> {
}