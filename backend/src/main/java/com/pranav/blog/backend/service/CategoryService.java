package com.pranav.blog.backend.service;

import com.pranav.blog.backend.dto.category.CategoryRequest;
import com.pranav.blog.backend.dto.category.CategoryResponse;
import com.pranav.blog.backend.entity.Category;
import com.pranav.blog.backend.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(
            CategoryRepository categoryRepository
    ) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponse createCategory(
            CategoryRequest request
    ) {

        if (categoryRepository.findByName(request.getName()).isPresent()) {
            throw new RuntimeException(
                    "Category already exists with name: "
                            + request.getName()
            );
        }

        if (categoryRepository.findBySlug(request.getSlug()).isPresent()) {
            throw new RuntimeException(
                    "Category already exists with slug: "
                            + request.getSlug()
            );
        }

        Category category = new Category();

        category.setName(request.getName());
        category.setSlug(request.getSlug());
        category.setDescription(request.getDescription());
        category.setActive(
                request.getActive() != null
                        ? request.getActive()
                        : true
        );

        Category savedCategory =
                categoryRepository.save(category);

        return mapToResponse(savedCategory);
    }

    public List<CategoryResponse> getAllCategories() {

        return categoryRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public CategoryResponse getCategoryById(
            Long id
    ) {

        Category category =
                categoryRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Category not found"
                                ));

        return mapToResponse(category);
    }

    private CategoryResponse mapToResponse(
            Category category
    ) {

        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getSlug(),
                category.getDescription(),
                category.getActive()
        );
    }
    public CategoryResponse updateCategory(
            Long id,
            CategoryRequest request
    ) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Category not found"
                        ));

        category.setName(request.getName());
        category.setSlug(request.getSlug());
        category.setDescription(request.getDescription());
        category.setActive(request.getActive());

        Category updatedCategory =
                categoryRepository.save(category);

        return mapToResponse(updatedCategory);
    }

    public void deleteCategory(
            Long id
    ) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Category not found"
                        ));

        categoryRepository.delete(category);
    }
}