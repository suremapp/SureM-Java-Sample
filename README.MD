# SureM REST API Java SDK 🚀

![Java](https://img.shields.io/badge/Java-11%2B-orange?style=for-the-badge&logo=java)
![JSON](https://img.shields.io/badge/JSON-REST_API-blue?style=for-the-badge)
![Build](https://img.shields.io/badge/build-passing-brightgreen?style=for-the-badge)

> **본 저장소는 슈어엠(SureM) 메시징 서비스를 자바 환경에서 연동하고자 하는 개발자를 위한 공식 샘플 소스코드입니다.**
> 별도의 SDK 설치 없이, 제공되는 코드를 프로젝트에 참조하거나 복사하여 즉시 연동 환경을 구축할 수 있습니다.

---

## 📌 개요 (Project Purpose)

슈어엠 API의 복잡한 인증 절차와 메시지 전송 로직을 가장 효율적인 표준 코드로 가이드합니다.
본 샘플 코드를 통해 API 스펙을 빠르게 검증하고 실제 서비스에 적용해 보세요.

---

## 🛠 Tech Stack & Dependencies

제공되는 샘플 코드는 성능과 안정성이 검증된 다음의 기술 스택을 기반으로 작성되었습니다.

* **Language:** Java 11+
* **HTTP Client:** `OkHttp 5.x` (고성능 커넥션 풀링 및 비동기 처리 지원)
* **JSON Parser:** `Gson 2.13.x` (유연한 데이터 직렬화)
* **Testing:** `JUnit 5` (발송 로직 검증)

---

## ⚙️ Prerequisites (Dependencies)

본 샘플 코드를 실행하기 위해 아래 의존성을 `build.gradle`에 추가해 주세요.

```gradle
dependencies {
    implementation 'com.squareup.okhttp3:okhttp:5.3.2'
    implementation 'com.google.code.gson:gson:2.13.2'
    compileOnly 'org.projectlombok:lombok:1.18.12'
    annotationProcessor 'org.projectlombok:lombok:1.18.12'
}
```

---

## 🚀 How to Use (Code Snippets)

1. 인증 및 토큰 갱신 (Authentication)
슈어엠 API는 보안을 위해 1시간 만료 토큰을 사용합니다. 샘플 코드 내 SureMClient 클래스는 토큰 만료 시 자동으로 재인증을 시도하는 로직을 포함하고 있습니다.

- SECRET_KEY 는 슈어엠 제공 사이트([슈어비즈](https://surebiz.co.kr)) 기본정보-내정보에서 확인 해주시기 바랍니다.
- 발신번호는 반드시 사전에 등록된 번호만 가능 합니다. 슈어엠 제공 사이트([슈어비즈](https://surebiz.co.kr)) 기본정보-발신번호에서 추가/관리 해주시기 바랍니다.

2. SMS 전송 예시 (SMSExample.java)

```java
// 샘플 클래스 초기화
SureMClient client = new SureMCleint("USER_CODE", "SECRET_KEY");

// 메시지 객체 생성 (Builder 패턴)
SureMModels.MessageRequest smsRequest = SureMModels.MessageRequest.builder()
        .to("01100000000")
        .reqPhone("15884640")
        .text("SMS 테스트 메시지 입니다. 최대 90byte 까지 발송 할 수 있습니다.")
//                .reservedTime("20990101000000") // 예약시간 설정 yyyyMMddHHmmss
        .build();

// 전송 실행
SureMModels.ApiResponse<Void> sendResult = client.sendMessage(SureMServiceType.SMS, smsRequest);
```


--- 

## 📂 Project Structure
```
src/main/java/com/surem/core
├── SureMClient.java      # 핵심 비즈니스 로직 (인증, 전송, 업로드)
├── SureMModels.java      # API 요청/응답 데이터 모델
└── SureMServiceType.java      # 전송 타입과 타입에 따른 PATH 상수 
```

---

## ⚠️ 중요 고지 (Disclaimer)

본 코드는 참조용 샘플 소스이며, 라이브러리(SDK) 형태의 정식 배포본이 아닙니다.

실제 운영 환경에 적용 시 고객사의 시스템 아키텍처에 맞게 예외 처리 및 로깅 정책을 반드시 추가하시기 바랍니다.

API 상세 스펙은 [슈어엠 개발자](https://developers.surem.com/) 센터를 참조하세요.

---

## 📄 License
Copyright © 2026 SureM Co., Ltd.


