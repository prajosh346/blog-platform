package com.pranav.blog.backend.service;

import com.pranav.blog.backend.dto.tag.TagRequest;
import com.pranav.blog.backend.dto.tag.TagResponse;
import com.pranav.blog.backend.entity.Tag;
import com.pranav.blog.backend.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(
            TagRepository tagRepository
    ) {
        this.tagRepository = tagRepository;
    }

    public TagResponse createTag(
            TagRequest request
    ) {

        if (tagRepository.findByName(request.getName()).isPresent()) {
            throw new RuntimeException(
                    "Tag already exists with name: "
                            + request.getName()
            );
        }

        if (tagRepository.findBySlug(request.getSlug()).isPresent()) {
            throw new RuntimeException(
                    "Tag already exists with slug: "
                            + request.getSlug()
            );
        }

        Tag tag = new Tag();

        tag.setName(request.getName());
        tag.setSlug(request.getSlug());
        tag.setDescription(request.getDescription());
        tag.setActive(
                request.getActive() != null
                        ? request.getActive()
                        : true
        );

        Tag savedTag = tagRepository.save(tag);

        return mapToResponse(savedTag);
    }

    public List<TagResponse> getAllTags() {

        return tagRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public TagResponse getTagById(
            Long id
    ) {

        Tag tag = tagRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Tag not found"
                        ));

        return mapToResponse(tag);
    }

    public TagResponse updateTag(
            Long id,
            TagRequest request
    ) {

        Tag tag = tagRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Tag not found"
                        ));

        tag.setName(request.getName());
        tag.setSlug(request.getSlug());
        tag.setDescription(request.getDescription());
        tag.setActive(request.getActive());

        Tag updatedTag = tagRepository.save(tag);

        return mapToResponse(updatedTag);
    }

    public void deleteTag(
            Long id
    ) {

        Tag tag = tagRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Tag not found"
                        ));

        tagRepository.delete(tag);
    }

    private TagResponse mapToResponse(
            Tag tag
    ) {

        return new TagResponse(
                tag.getId(),
                tag.getName(),
                tag.getSlug(),
                tag.getDescription(),
                tag.getActive()
        );
    }
}