package com.surem.example;

import com.surem.core.SureMClient;
import com.surem.core.SureMModels;
import com.surem.core.SureMServiceType;

import java.io.File;
import java.net.URL;

public class INTLExample {

    public static void sendINTL(SureMClient client) throws Exception {

        int messageId = client.getMessageId();
        SureMModels.MessageRequest intlRequest = SureMModels.MessageRequest.builder()
                .country("86")
                .to("01100000000")
                .reqPhone("15884640")
                .text("INTL 테스트 메시지 입니다. 장문인 경우 concat-SMS로 전송됩니다. \nLorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum")
//                .reservedTime("20990101000000") // 예약시간 설정 yyyyMMddHHmmss
                .messageId(messageId)
                .build();

        SureMModels.ApiResponse<Void> sendResult = client.sendMessage(SureMServiceType.INTL, intlRequest);
        System.out.printf("국제메시지 텍스트 발송 결과: [messageId:%d, ackCode:%s]%n", messageId, sendResult.getCode());
    }
}
