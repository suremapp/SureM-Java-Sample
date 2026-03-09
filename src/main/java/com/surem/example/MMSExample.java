package com.surem.example;

import com.surem.core.SureMClient;
import com.surem.core.SureMModels;
import com.surem.core.SureMServiceType;

import java.io.File;
import java.net.URL;

public class MMSExample {

    public static void sendLMS(SureMClient client) throws Exception {

        SureMModels.MessageRequest smsRequest = SureMModels.MessageRequest.builder()
                .to("01100000000")
                .reqPhone("15884640")
                .text("LMS 테스트 메시지 입니다. 최대 2000byte 까지 발송 할 수 있습니다. \nLorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum")
//                .reservedTime("20990101000000") // 예약시간 설정 yyyyMMddHHmmss
                .build();

        SureMModels.ApiResponse<Void> sendResult = client.sendMessage(SureMServiceType.MMS, smsRequest);
        System.out.println("LMS 발송 결과: " + sendResult.getCode());
    }


    public static void sendMMS(SureMClient client) throws Exception {
        URL url = MMSExample.class.getResource("/kakaoFriend.jpg");
        if ( url == null ) {
            throw new RuntimeException("Image not found");
        }

        File file = new File(url.getFile());

        SureMModels.ApiResponse<SureMModels.ImageInfo> imageKeyResponse = client.uploadImage(file);
        String imageKey = imageKeyResponse.getData().getImageKey();

        SureMModels.MessageRequest smsRequest = SureMModels.MessageRequest.builder()
                .to("01100000000")
                .reqPhone("15884640")
                .text("LMS 테스트 메시지 입니다. 최대 2000byte 까지 발송 할 수 있습니다. \nLorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum")
//                .reservedTime("20990101000000") // 예약시간 설정 yyyyMMddHHmmss
                .imageKey(imageKey)
                .build();

        SureMModels.ApiResponse<Void> sendResult = client.sendMessage(SureMServiceType.MMS, smsRequest);
        System.out.println("MMS 발송 결과: " + sendResult.getCode());
    }
}
