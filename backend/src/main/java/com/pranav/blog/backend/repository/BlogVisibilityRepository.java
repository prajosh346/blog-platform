package com.pranav.blog.backend.repository;

import com.pranav.blog.backend.entity.Blog;
import com.pranav.blog.backend.entity.BlogVisibility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogVisibilityRepository
        extends JpaRepository<BlogVisibility, Long> {

    List<BlogVisibility> findByBlog(Blog blog);

    void deleteByBlog(Blog blog);
}