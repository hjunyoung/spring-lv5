package com.example.springlv5.global;

import lombok.Builder;
import lombok.Getter;

public class ApiResponse {
    private static final String SUCCESS_STATUS = "success";
    private static final String ERROR_STATUS = "fail";
    @Getter
    @Builder
    public static class SuccessBody<T> {
        @Builder.Default
        private String status = SUCCESS_STATUS;
        @Builder.Default
        private String message = "Success message";
        private T data;
    }

    @Getter
    @Builder
    public static class FailBody {
        @Builder.Default
        private String status = ERROR_STATUS;
        @Builder.Default
        private String message = "Error message";
    }
}
