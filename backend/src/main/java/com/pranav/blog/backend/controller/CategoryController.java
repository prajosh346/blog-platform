package com.pranav.blog.backend.controller;

import com.pranav.blog.backend.dto.category.CategoryRequest;
import com.pranav.blog.backend.dto.category.CategoryResponse;
import com.pranav.blog.backend.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(
            CategoryService categoryService
    ) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public CategoryResponse createCategory(
            @RequestBody CategoryRequest request
    ) {
        return categoryService.createCategory(request);
    }

    @GetMapping
    public List<CategoryResponse> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(
            @PathVariable Long id
    ) {
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/{id}")
    public CategoryResponse updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryRequest request
    ) {
        return categoryService.updateCategory(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(
            @PathVariable Long id
    ) {
        categoryService.deleteCategory(id);
        return "Category deleted successfully";
    }
}