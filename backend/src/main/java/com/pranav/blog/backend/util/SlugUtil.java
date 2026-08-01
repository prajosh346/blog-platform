package com.pranav.blog.backend.util;

public final class SlugUtil {

    private SlugUtil() {
    }

    public static String toSlug(String value) {

        if (value == null || value.isBlank()) {
            return "";
        }

        return value.toLowerCase()
                .trim()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-")
                .replaceAll("-+", "-");
    }

}