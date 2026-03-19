package com.surem.example;

import com.surem.core.SureMClient;
import com.surem.core.SureMModels;
import com.surem.core.SureMServiceType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlimtalkExample {

    private static final String SENDER_KEY = "YOUR_SENDERKEY";

    public static void sendAlimtalkText(SureMClient client) throws Exception {

        int messageId = client.getMessageId();
        SureMModels.KakaoRequest kkoRequest = SureMModels.KakaoRequest.builder()
                .to("{수신번호}")
                .reqPhone("{발신번호}")
                .text("알림톡 본문 내용")
//                .reservedTime("20990101000000") // 예약시간 설정 yyyyMMddHHmmss
                .bizType("AT")
                .senderKey(SENDER_KEY)
                .templateCode("{템플릿코드}")
                .attachment(null)
                .messageId(messageId)
                .build();

        SureMModels.ApiResponse<Void> sendResult = client.sendMessage(SureMServiceType.ALIMTALK, kkoRequest);
        System.out.printf("알림톡 텍스트 발송 결과: [messageId:%d, ackCode:%s]%n", messageId, sendResult.getCode());
    }

    public static void sendAlimtalkTextWithButtons(SureMClient client) throws Exception {

        List<SureMModels.KakaoButton> buttonMeta = new ArrayList<>();

        buttonMeta.add(SureMModels.KakaoButton.builder()
                .type("AC")
                .name("채널 추가")
                .build());

        buttonMeta.add(SureMModels.KakaoButton.builder()
                .type("WL")
                .name("슈어비즈 바로가기")
                .url_mobile("https://m.surem.com")
                .url_pc("https://surebiz.co.kr")
                .build());

        int messageId = client.getMessageId();
        SureMModels.KakaoRequest kkoRequest = SureMModels.KakaoRequest.builder()
                .to("{수신번호}")
                .reqPhone("{발신번호}")
                .text("알림톡 본문 내용")
//                .reservedTime("20990101000000") // 예약시간 설정 yyyyMMddHHmmss
                .bizType("AT")
                .senderKey(SENDER_KEY)
                .templateCode("{템플릿코드}")
                .attachment(makeAttachment(buttonMeta))         // 버튼정보
                .messageId(messageId)
                .build();

        SureMModels.ApiResponse<Void> sendResult = client.sendMessage(SureMServiceType.ALIMTALK, kkoRequest);
        System.out.printf("알림톡 텍스트+버튼 발송 결과: [messageId:%d, ackCode:%s]%n", messageId, sendResult.getCode());
    }


    public static void sendBrandMessageText(SureMClient client) throws Exception {

        int messageId = client.getMessageId();
        SureMModels.KakaoRequest kkoRequest = SureMModels.KakaoRequest.builder()
                .to("{수신번호}")
                .reqPhone("{발신번호}")
                .text("브랜드메시지 텍스트형 전송 테스트 입니다.")
//                .reservedTime("20990101000000") // 예약시간 설정 yyyyMMddHHmmss
                .bizType("FT")
                .senderKey(SENDER_KEY)
                .targeting("M")
                .messageId(messageId)
                .build();

        SureMModels.ApiResponse<Void> sendResult = client.sendMessage(SureMServiceType.BRAND_MESSAGE, kkoRequest);
        System.out.printf("브랜드메시지 텍스트형 발송 결과: [messageId:%d, ackCode:%s]%n", messageId, sendResult.getCode());
    }


    private static Map<String, Object> makeAttachment(List<SureMModels.KakaoButton> buttonMeta) {
        Map<String, Object> attachment = new HashMap<>();

        List<Map<String, Object>> buttons = new ArrayList<>();

        for (SureMModels.KakaoButton kakaoButton : buttonMeta) {
            Map<String, Object> button = new HashMap<>();
            button.put("name", kakaoButton.getName());
            button.put("type", kakaoButton.getType());
            button.put("url_mobile", kakaoButton.getUrl_mobile());
            button.put("url_pc", kakaoButton.getUrl_pc());
            buttons.add(button);
        }

        attachment.put("button", buttons);

        return attachment;

    }


}
