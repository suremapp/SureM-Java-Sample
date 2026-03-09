package com.surem.core;

import lombok.*;

public class SureMModels {

    @Data @Builder
    public static class AuthRequest {
        private String userCode;
        private String secretKey;
    }

    @Data
    public static class AuthResponse {
        private String accessToken;
    }

    @Data @Builder
    @AllArgsConstructor @NoArgsConstructor
    public static class MessageRequest {
        private String to;
        private String text;
        private String reqPhone;
        private String reservedTime;
        private Integer messageId;
        private String origCode;
        private String imageKey;
    }

    @Data
    public static class ApiResponse<T> {
        private String code;
        private String message;
        private T data;
    }

    @Data
    public static class ImageInfo {
        private String imageKey;
        private String expiryDate;
    }
}
