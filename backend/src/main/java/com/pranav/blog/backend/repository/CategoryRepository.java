package com.pranav.blog.backend.repository;

import com.pranav.blog.backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository
        extends JpaRepository<Category, Long> {

    Optional<Category> findBySlug(String slug);
}