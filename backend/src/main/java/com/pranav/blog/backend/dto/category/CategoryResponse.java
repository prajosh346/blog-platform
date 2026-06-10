package com.pranav.blog.backend.dto.category;

public class CategoryResponse {

    private Long id;
    private String name;
    private String slug;
    private String description;
    private Boolean active;

    public CategoryResponse(
            Long id,
            String name,
            String slug,
            String description,
            Boolean active
    ) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getActive() {
        return active;
    }
}