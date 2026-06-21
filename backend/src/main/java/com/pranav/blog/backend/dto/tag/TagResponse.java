package com.pranav.blog.backend.dto.tag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TagResponse {

    private Long id;
    private String name;
    private String slug;
    private String description;
    private Boolean active;
}