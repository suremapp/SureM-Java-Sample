package com.surem.example;

import com.surem.core.SureMClient;
import com.surem.core.SureMModels;
import com.surem.core.SureMServiceType;

public class SMSExample {

    public static void sendSMS(SureMClient client) throws Exception {

        int messageId = client.getMessageId();
        SureMModels.MessageRequest smsRequest = SureMModels.MessageRequest.builder()
                .to("{수신번호}")
                .reqPhone("{발신번호}")
                .text("SMS 테스트 메시지 입니다. 최대 90byte 까지 발송 할 수 있습니다.")
//                .reservedTime("20990101000000") // 예약시간 설정 yyyyMMddHHmmss
                .messageId(messageId)
                .build();

        SureMModels.ApiResponse<Void> sendResult = client.sendMessage(SureMServiceType.SMS, smsRequest);
        System.out.printf("SMS 발송 결과: [messageId:%d / ackCode:%s]%n", messageId, sendResult.getCode());
    }
}