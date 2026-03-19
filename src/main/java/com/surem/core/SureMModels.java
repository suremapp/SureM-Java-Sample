package com.surem.core;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Map;

public class SureMModels {

    @Data
    @Builder
    public static class AuthRequest {
        private String userCode;
        private String secretKey;
    }

    @Data
    public static class AuthResponse {
        private String accessToken;
    }

    @SuperBuilder
    public static class MessageRequest extends BaseRequest {
        private String text;
        private String reqPhone;
        private String reservedTime;
        private String origCode;
        private String imageKey;
        private String country;
    }


    @SuperBuilder
    public static class KakaoRequest extends BaseRequest {
        private String text;
        private String reqPhone;
        private String reservedTime;
        private String origCode;
        private String imageKey;
        private String bizType;
        private String senderKey;
        private String templateCode;
        private String templateTitle;
        private String reSend;
        private String reSubject;
        private String reText;
        private Map<String, Object> attachment;
        private Map<String, Object> supplement;
        private Map<String, Object> carousel;
        private String additionalContent;
        private String header;
        private String targeting;
    }

    @Builder
    @Getter
    public static class KakaoButton {
        private String type;
        private String name;
        private String url_mobile;
        private String url_pc;
        private String scheme_android;
        private String scheme_ios;
        private String target;
    }

    @SuperBuilder
    public static class BaseRequest {
        private String to;
        private int messageId;
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
