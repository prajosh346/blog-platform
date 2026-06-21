package com.pranav.blog.backend.controller;

import com.pranav.blog.backend.dto.tag.TagRequest;
import com.pranav.blog.backend.dto.tag.TagResponse;
import com.pranav.blog.backend.service.TagService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagService tagService;

    public TagController(
            TagService tagService
    ) {
        this.tagService = tagService;
    }

    @PostMapping
    public TagResponse createTag(
            @Valid @RequestBody TagRequest request
    ) {
        return tagService.createTag(request);
    }

    @GetMapping
    public List<TagResponse> getAllTags() {
        return tagService.getAllTags();
    }

    @GetMapping("/{id}")
    public TagResponse getTagById(
            @PathVariable Long id
    ) {
        return tagService.getTagById(id);
    }

    @PutMapping("/{id}")
    public TagResponse updateTag(
            @PathVariable Long id,
            @Valid @RequestBody TagRequest request
    ) {
        return tagService.updateTag(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteTag(
            @PathVariable Long id
    ) {
        tagService.deleteTag(id);
        return "Tag deleted successfully";
    }
}