package com.pranav.blog.backend.repository;

import com.pranav.blog.backend.entity.Blog;
import com.pranav.blog.backend.entity.BlogTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogTagRepository
        extends JpaRepository<BlogTag, Long> {

    List<BlogTag> findByBlog(Blog blog);

    void deleteByBlog(Blog blog);
}