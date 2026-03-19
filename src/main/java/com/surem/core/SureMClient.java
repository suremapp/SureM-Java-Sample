package com.surem.core;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class SureMClient {

    private static final String BASE_URL = "https://rest.surem.com";
    private static final String SUB_URL = "https://gw.surem.com";
    private final OkHttpClient httpClient = new OkHttpClient();
    private final Gson gson = new Gson();

    private final String userCode;
    private final String secretKey;
    private String accessToken;
    private LocalDateTime tokenExpiry;

    private static final AtomicInteger messageId  = new AtomicInteger(0);

    public int getMessageId() {
        return messageId.incrementAndGet();
    }


    public SureMClient(String userCode, String secretKey) {
        this.userCode = userCode;
        this.secretKey = secretKey;
    }

    /**
     * 토큰 획득 및 유효성 체크 (자동 갱신)
     */
    private synchronized String getValidToken() throws IOException {
        if (accessToken == null || LocalDateTime.now().isAfter(tokenExpiry)) {
            authenticate();
        }
        return accessToken;
    }

    private void authenticate() throws IOException {
        SureMModels.AuthRequest authReq = SureMModels.AuthRequest.builder()
                .userCode(userCode)
                .secretKey(secretKey)
                .build();

        RequestBody body = RequestBody.create(
                gson.toJson(authReq), MediaType.get("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(BASE_URL + SureMServiceType.AUTH.servicePath)
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            SureMModels.ApiResponse<SureMModels.AuthResponse> authRes = gson.fromJson(response.body().string(),
                    new TypeToken<SureMModels.ApiResponse<SureMModels.AuthResponse>>() {
                    }.getType());

            if (!response.isSuccessful()) throw new IOException("인증 실패: " + authRes.toString());


            this.accessToken = authRes.getData().getAccessToken();
            // 만료시간 1시간 설정 (안전을 위해 55분으로 설정 권장)
            this.tokenExpiry = LocalDateTime.now().plusMinutes(55);
        }
    }

    /**
     * 이미지 업로드 (MMS용)
     *
     * @param images 업로드할 이미지 파일 리스트 (최대 3개)
     */
    public SureMModels.ApiResponse<SureMModels.ImageInfo> uploadImage(File... images) throws IOException {
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);

        for (int i = 0; i < images.length; i++) {
            if (i >= 3) break; // 최대 3개 제한
            builder.addFormDataPart("image" + (i + 1), images[i].getName(),
                    RequestBody.create(images[i], MediaType.parse("image/jpeg")));

        }

        Request request = new Request.Builder()
                .url(BASE_URL + "/api/v1/image")
                .addHeader("Authorization", "Bearer " + getValidToken())
                .post(builder.build())
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            return gson.fromJson(response.body().string(),
                    new TypeToken<SureMModels.ApiResponse<SureMModels.ImageInfo>>() {
                    }.getType());

        }
    }

    /**
     * 메시지 전송 (SMS/MMS 공통)
     */
    public SureMModels.ApiResponse<Void> sendMessage(SureMServiceType type, SureMModels.BaseRequest message) throws IOException {
        String path = type.servicePath;

        RequestBody body = RequestBody.create(
                gson.toJson(message), MediaType.get("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(BASE_URL + path)
                .addHeader("Authorization", "Bearer " + getValidToken())
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            return gson.fromJson(response.body().string(),
                    new TypeToken<SureMModels.ApiResponse<Void>>() {
                    }.getType());
        }
    }

}
