package com.surem.example;

import com.surem.sdk.java.SureMClient;
import com.surem.sdk.java.SureMModels;

public class SMSExample {

    public static void sendSMS(SureMClient client) throws Exception {

        SureMModels.MessageRequest smsRequest = SureMModels.MessageRequest.builder()
                .to("01100000000")
                .reqPhone("15884640")
                .text("SMS 테스트 메시지 입니다. 최대 90byte 까지 발송 할 수 있습니다.")
//                .reservedTime("20990101000000") // 예약시간 설정 yyyyMMddHHmmss
                .build();

        SureMModels.ApiResponse<Void> sendResult = client.sendMessage("sms", smsRequest);
        System.out.println("SMS 발송 결과: " + sendResult.getCode());
    }
}