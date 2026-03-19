package com.surem.example;

import com.surem.core.SureMClient;
import com.surem.core.SureMModels;
import com.surem.core.SureMServiceType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlimtalkExample {


    public static void sendAlimtalkText(SureMClient client) throws Exception {

        int messageId = client.getMessageId();
        SureMModels.KakaoRequest kkoRequest = SureMModels.KakaoRequest.builder()
                .to("821011112222")
                .reqPhone("15884640")
                .text("#{고객명} 고객님.\n" +
                        "글래드마포 호텔 예약이 완료되었습니다.")
//                .reservedTime("20990101000000") // 예약시간 설정 yyyyMMddHHmmss
                .bizType("AT")
                .senderKey("a6fb47772e56c22e5ae7a6cc77032498c9b44073")
                .templateCode("gladmapo_001")
                .attachment(null)
                .messageId(messageId)
                .build();

        SureMModels.ApiResponse<Void> sendResult = client.sendMessage(SureMServiceType.ALIMTALK, kkoRequest);
        System.out.printf("알림톡 텍스트 발송 결과: [messageId:%d, ackCode:%s]%n", messageId, sendResult.getCode());
    }

    public static void sendAlimtalkTextWithButtons(SureMClient client) throws Exception {

        List<SureMModels.KakaoButton> buttonMeta = new ArrayList<>();
        buttonMeta.add(SureMModels.KakaoButton.builder()
                .type("WL")
                .name("이용가이드보기")
                .url_mobile("https://drive.google.com/file/d/19sU7rERO2MHahoRMJhwA_OAXW3rvkWHx/view?usp=sharing")
                .url_pc("https://drive.google.com/file/d/19sU7rERO2MHahoRMJhwA_OAXW3rvkWHx/view?usp=sharing")
                .build());

        buttonMeta.add(SureMModels.KakaoButton.builder()
                .type("WL")
                .name("요금제 확인하기")
                .url_mobile("https://drive.google.com/file/d/1oY2Ii21AvnPM3AYqw8kmHZgvJfIQzQ7V/view?usp=sharing")
                .url_pc("https://drive.google.com/file/d/1oY2Ii21AvnPM3AYqw8kmHZgvJfIQzQ7V/view?usp=sharing")
                .build());

        buttonMeta.add(SureMModels.KakaoButton.builder()
                .type("WL")
                .name("이용신청서 다운로드받기")
                .url_mobile("http://www.surem.co.kr/business/business_proposal.asp")
                .url_pc("http://www.surem.co.kr/business/business_proposal.asp")
                .build());


        int messageId = client.getMessageId();
        SureMModels.KakaoRequest kkoRequest = SureMModels.KakaoRequest.builder()
                .to("821000000000")
                .reqPhone("15884640")
                .text("고객님의 서비스신청이 접수되었습니다. (미소)\n" +
                        "담당자는 #{이름} (#{메일주소}) 입니다.\n" +
                        "\n" +
                        "알림톡 이용을 위해서는 아래와 같은 절차를 따라주시면 됩니다. (하하)\n" +
                        "1. 요금제 선택\n" +
                        "2. 이용신청서 작성\n" +
                        "3. 이용신청 및 필수서류 제출\n" +
                        "- 개인사업자 : 사업자등록증, 대표자 신분증사본(주민번호 뒷자리 삭제)\n" +
                        "- 법인사업자 : 사업자등록증, 법인인감증명서\n" +
                        "\n" +
                        "서류는 1차적으로 스캔본을 담당자의 메일로 발송해 주시기 바라며, 원본은 우편발송 해주시기 바랍니다. (굿)\n" +
                        "\n" +
                        "(서울시 송파구 오금로81 송파빌딩 11층 슈어엠)")
//                .reservedTime("20990101000000") // 예약시간 설정 yyyyMMddHHmmss
                .bizType("AT")
                .senderKey("a6fb47772e56c22e5ae7a6cc77032498c9b44073")
                .templateCode("cs_01")
                .attachment(makeAttachment(buttonMeta))         // 버튼정보
                .messageId(messageId)
                .build();

        SureMModels.ApiResponse<Void> sendResult = client.sendMessage(SureMServiceType.ALIMTALK, kkoRequest);
        System.out.printf("알림톡 텍스트+버튼 발송 결과: [messageId:%d, ackCode:%s]%n", messageId, sendResult.getCode());
    }


    public static void sendBrandMessageText(SureMClient client) throws Exception {

        int messageId = client.getMessageId();
        SureMModels.KakaoRequest kkoRequest = SureMModels.KakaoRequest.builder()
                .to("821024796777")
                .reqPhone("15884640")
                .text("브랜드메시지 텍스트형 전송 테스트 입니다.")
//                .reservedTime("20990101000000") // 예약시간 설정 yyyyMMddHHmmss
                .bizType("FT")
                .senderKey("a6fb47772e56c22e5ae7a6cc77032498c9b44073")
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
