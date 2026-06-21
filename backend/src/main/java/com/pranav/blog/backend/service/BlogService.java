package com.pranav.blog.backend.service;

import com.pranav.blog.backend.dto.blog.BlogRequest;
import com.pranav.blog.backend.dto.blog.BlogResponse;
import com.pranav.blog.backend.entity.*;
import com.pranav.blog.backend.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final BlogTagRepository blogTagRepository;
    private final VisibilityTypeRepository visibilityTypeRepository;
    private final BlogVisibilityRepository blogVisibilityRepository;

    public BlogService(
            BlogRepository blogRepository,
            UserRepository userRepository,
            CategoryRepository categoryRepository,
            TagRepository tagRepository,
            BlogTagRepository blogTagRepository,
            VisibilityTypeRepository visibilityTypeRepository,
            BlogVisibilityRepository blogVisibilityRepository
    ) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
        this.blogTagRepository = blogTagRepository;
        this.visibilityTypeRepository = visibilityTypeRepository;
        this.blogVisibilityRepository = blogVisibilityRepository;
    }

    public BlogResponse createBlog(BlogRequest request) {

        if (blogRepository.existsBySlug(request.getSlug())) {
            throw new RuntimeException("Slug already exists");
        }

        User author = userRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new EntityNotFoundException("Author not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        Blog blog = new Blog();

        blog.setTitle(request.getTitle());
        blog.setSlug(request.getSlug());
        blog.setExcerpt(request.getExcerpt());
        blog.setFeaturedImageUrl(request.getFeaturedImageUrl());
        blog.setContent(request.getContent());

        blog.setAuthor(author);
        blog.setCategory(category);

        blog.setStatus(request.getStatus());
        blog.setLanguage(request.getLanguage());

        blog.setSeoTitle(request.getSeoTitle());
        blog.setSeoDescription(request.getSeoDescription());
        blog.setSeoKeywords(request.getSeoKeywords());

        blog.setCommentsEnabled(request.getCommentsEnabled());

        if (request.getStatus() == BlogStatus.PUBLISHED) {
            blog.setPublishedAt(LocalDateTime.now());
        }

        Blog savedBlog = blogRepository.save(blog);

        List<String> tagNames = new ArrayList<>();

        if (request.getTagIds() != null) {

            for (Long tagId : request.getTagIds()) {

                Tag tag = tagRepository.findById(tagId)
                        .orElseThrow(() ->
                                new EntityNotFoundException("Tag not found: " + tagId));

                BlogTag blogTag = new BlogTag();
                blogTag.setBlog(savedBlog);
                blogTag.setTag(tag);

                blogTagRepository.save(blogTag);

                tagNames.add(tag.getName());
            }
        }

        List<String> visibilityNames = new ArrayList<>();

        if (request.getVisibilityTypeIds() != null) {

            for (Long visibilityId : request.getVisibilityTypeIds()) {

                VisibilityType visibilityType =
                        visibilityTypeRepository.findById(visibilityId)
                                .orElseThrow(() ->
                                        new EntityNotFoundException(
                                                "Visibility Type not found: " + visibilityId));

                BlogVisibility blogVisibility = new BlogVisibility();
                blogVisibility.setBlog(savedBlog);
                blogVisibility.setVisibilityType(visibilityType);

                blogVisibilityRepository.save(blogVisibility);

                visibilityNames.add(visibilityType.getName());
            }
        }

        BlogResponse response = new BlogResponse();

        response.setId(savedBlog.getId());
        response.setTitle(savedBlog.getTitle());
        response.setSlug(savedBlog.getSlug());
        response.setExcerpt(savedBlog.getExcerpt());
        response.setFeaturedImageUrl(savedBlog.getFeaturedImageUrl());
        response.setContent(savedBlog.getContent());

        response.setAuthorName(
                author.getFirstName() + " " + author.getLastName()
        );

        response.setCategoryName(category.getName());

        response.setTags(tagNames);
        response.setVisibilityTypes(visibilityNames);

        response.setStatus(savedBlog.getStatus());
        response.setViewCount(savedBlog.getViewCount());

        response.setLanguage(savedBlog.getLanguage());

        response.setSeoTitle(savedBlog.getSeoTitle());
        response.setSeoDescription(savedBlog.getSeoDescription());
        response.setSeoKeywords(savedBlog.getSeoKeywords());

        response.setCommentsEnabled(savedBlog.getCommentsEnabled());

        response.setPublishedAt(savedBlog.getPublishedAt());
        response.setCreatedAt(savedBlog.getCreatedAt());
        response.setUpdatedAt(savedBlog.getUpdatedAt());

        return response;
    }
    public List<BlogResponse> getAllBlogs() {

        return blogRepository.findByDeletedFalse()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    public BlogResponse getBlogBySlug(String slug) {

        Blog blog = blogRepository
                .findBySlugAndDeletedFalse(slug)
                .orElseThrow(() ->
                        new EntityNotFoundException("Blog not found"));

        blog.setViewCount(blog.getViewCount() + 1);

        blogRepository.save(blog);

        return mapToResponse(blog);
    }
    public BlogResponse getBlogById(Long id) {

        Blog blog = blogRepository
                .findByIdAndDeletedFalse(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Blog not found"));

        return mapToResponse(blog);
    }
    public BlogResponse updateBlog(
            Long id,
            BlogRequest request
    ) {

        Blog blog = blogRepository
                .findByIdAndDeletedFalse(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Blog not found"));

        User author = userRepository.findById(
                request.getAuthorId()
        ).orElseThrow(() ->
                new EntityNotFoundException("Author not found"));

        Category category = categoryRepository.findById(
                request.getCategoryId()
        ).orElseThrow(() ->
                new EntityNotFoundException("Category not found"));

        blog.setTitle(request.getTitle());
        blog.setSlug(request.getSlug());
        blog.setExcerpt(request.getExcerpt());
        blog.setFeaturedImageUrl(request.getFeaturedImageUrl());
        blog.setContent(request.getContent());

        blog.setAuthor(author);
        blog.setCategory(category);

        blog.setStatus(request.getStatus());
        blog.setLanguage(request.getLanguage());

        blog.setSeoTitle(request.getSeoTitle());
        blog.setSeoDescription(request.getSeoDescription());
        blog.setSeoKeywords(request.getSeoKeywords());

        blog.setCommentsEnabled(
                request.getCommentsEnabled()
        );

        Blog updatedBlog = blogRepository.save(blog);

        blogTagRepository.deleteByBlog(updatedBlog);

        if (request.getTagIds() != null) {

            for (Long tagId : request.getTagIds()) {

                Tag tag = tagRepository.findById(tagId)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Tag not found: " + tagId));

                BlogTag blogTag = new BlogTag();
                blogTag.setBlog(updatedBlog);
                blogTag.setTag(tag);

                blogTagRepository.save(blogTag);
            }
        }

        blogVisibilityRepository.deleteByBlog(updatedBlog);

        if (request.getVisibilityTypeIds() != null) {

            for (Long visibilityId :
                    request.getVisibilityTypeIds()) {

                VisibilityType visibilityType =
                        visibilityTypeRepository
                                .findById(visibilityId)
                                .orElseThrow(() ->
                                        new EntityNotFoundException(
                                                "Visibility Type not found: "
                                                        + visibilityId));

                BlogVisibility blogVisibility =
                        new BlogVisibility();

                blogVisibility.setBlog(updatedBlog);
                blogVisibility.setVisibilityType(
                        visibilityType
                );

                blogVisibilityRepository.save(
                        blogVisibility
                );
            }
        }

        return mapToResponse(updatedBlog);
    }
    public void deleteBlog(Long id) {

        Blog blog = blogRepository
                .findByIdAndDeletedFalse(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Blog not found"));

        blog.setDeleted(true);

        blogRepository.save(blog);
    }
    private BlogResponse mapToResponse(Blog blog) {

        BlogResponse response = new BlogResponse();

        response.setId(blog.getId());
        response.setTitle(blog.getTitle());
        response.setSlug(blog.getSlug());
        response.setExcerpt(blog.getExcerpt());
        response.setFeaturedImageUrl(blog.getFeaturedImageUrl());
        response.setContent(blog.getContent());

        response.setAuthorName(
                blog.getAuthor().getFirstName()
                        + " "
                        + blog.getAuthor().getLastName()
        );

        response.setCategoryName(
                blog.getCategory().getName()
        );

        response.setStatus(blog.getStatus());
        response.setViewCount(blog.getViewCount());

        response.setLanguage(blog.getLanguage());

        response.setSeoTitle(blog.getSeoTitle());
        response.setSeoDescription(
                blog.getSeoDescription()
        );
        response.setSeoKeywords(
                blog.getSeoKeywords()
        );

        response.setCommentsEnabled(
                blog.getCommentsEnabled()
        );

        response.setPublishedAt(
                blog.getPublishedAt()
        );

        response.setCreatedAt(
                blog.getCreatedAt()
        );

        response.setUpdatedAt(
                blog.getUpdatedAt()
        );

        List<String> tags =
                blogTagRepository.findByBlog(blog)
                        .stream()
                        .map(bt -> bt.getTag().getName())
                        .toList();

        response.setTags(tags);

        List<String> visibilityTypes =
                blogVisibilityRepository
                        .findByBlog(blog)
                        .stream()
                        .map(v ->
                                v.getVisibilityType().getName())
                        .toList();

        response.setVisibilityTypes(
                visibilityTypes
        );

        return response;
    }
}