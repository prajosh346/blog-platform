package com.pranav.blog.backend.common;

import java.time.LocalDateTime;

public class ApiResponseUtil {

    private ApiResponseUtil() {
    }

    public static <T> ApiResponse<T> success(
            String message,
            T data
    ) {

        return new ApiResponse<>(
                true,
                message,
                data,
                LocalDateTime.now()
        );
    }

    public static ApiResponse<Void> success(
            String message
    ) {

        return new ApiResponse<>(
                true,
                message,
                null,
                LocalDateTime.now()
        );
    }

}