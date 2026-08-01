package com.pranav.blog.backend.validation;

public final class ValidationUtil {

    private ValidationUtil() {
    }

    public static void require(
            boolean condition,
            String message
    ) {

        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notNull(
            Object object,
            String message
    ) {

        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notBlank(
            String value,
            String message
    ) {

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }

}