package com.pranav.blog.backend.controller;

import com.pranav.blog.backend.dto.blog.BlogRequest;
import com.pranav.blog.backend.dto.blog.BlogResponse;
import com.pranav.blog.backend.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping
    public ResponseEntity<BlogResponse> createBlog(
            @Valid @RequestBody BlogRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(blogService.createBlog(request));
    }

    @GetMapping
    public ResponseEntity<List<BlogResponse>> getAllBlogs() {

        return ResponseEntity.ok(
                blogService.getAllBlogs()
        );
    }

    @GetMapping("/{slug}")
    public ResponseEntity<BlogResponse> getBlogBySlug(
            @PathVariable String slug
    ) {

        return ResponseEntity.ok(
                blogService.getBlogBySlug(slug)
        );
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BlogResponse> getBlogById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                blogService.getBlogById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogResponse> updateBlog(
            @PathVariable Long id,
            @Valid @RequestBody BlogRequest request
    ) {

        return ResponseEntity.ok(
                blogService.updateBlog(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBlog(
            @PathVariable Long id
    ) {

        blogService.deleteBlog(id);

        return ResponseEntity.ok(
                "Blog deleted successfully"
        );
    }
}