package com.pranav.blog.backend.dto.tag;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagRequest {

    @NotBlank(message = "Tag name is required")
    private String name;

    @NotBlank(message = "Tag slug is required")
    private String slug;

    private String description;

    private Boolean active = true;
}