package com.pranav.blog.backend.repository;

import com.pranav.blog.backend.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    Optional<Blog> findBySlug(String slug);

    boolean existsBySlug(String slug);

    List<Blog> findByDeletedFalse();

    Optional<Blog> findByIdAndDeletedFalse(Long id);

    Optional<Blog> findBySlugAndDeletedFalse(String slug);
}