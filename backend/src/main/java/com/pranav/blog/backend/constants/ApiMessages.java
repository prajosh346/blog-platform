package com.pranav.blog.backend.constants;

public final class ApiMessages {

    private ApiMessages() {
    }

    // Common

    public static final String CREATED = "Created successfully";
    public static final String UPDATED = "Updated successfully";
    public static final String DELETED = "Deleted successfully";
    public static final String FETCHED = "Fetched successfully";

    // Blog

    public static final String BLOG_CREATED = "Blog created successfully";
    public static final String BLOG_UPDATED = "Blog updated successfully";
    public static final String BLOG_DELETED = "Blog deleted successfully";
    public static final String BLOG_NOT_FOUND = "Blog not found";

    // Category

    public static final String CATEGORY_CREATED = "Category created successfully";
    public static final String CATEGORY_UPDATED = "Category updated successfully";
    public static final String CATEGORY_DELETED = "Category deleted successfully";
    public static final String CATEGORY_NOT_FOUND = "Category not found";

    // Tag

    public static final String TAG_CREATED = "Tag created successfully";
    public static final String TAG_UPDATED = "Tag updated successfully";
    public static final String TAG_DELETED = "Tag deleted successfully";
    public static final String TAG_NOT_FOUND = "Tag not found";

    // User

    public static final String USER_NOT_FOUND = "User not found";

    // Auth

    public static final String LOGIN_SUCCESS = "Login successful";
    public static final String INVALID_CREDENTIALS = "Invalid username or password";
}