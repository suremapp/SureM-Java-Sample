# Repository Guidelines

## Project Structure & Module Organization
This repository is a small Java sample SDK in `src/main`. Use this layout:
- `src/main/java/com/surem/core`: production API client and models (`SureMClient`, `SureMModels`, `SureMServiceType`)
- `src/main/java/com/surem/example`: runnable sample code (`Main`, `SMSExample`, `MMSExample`, `AlimtalkExample`, `INTLExample`)
- `src/main/resources`: sample assets (e.g., `kakaoFriend.jpg`) for examples
- `build.gradle`: build and dependency definitions
- `gradlew` / `gradlew.bat`: Gradle wrapper scripts

## Build, Test, and Development Commands
- `./gradlew test`: compile and run tests.
- `./gradlew build`: compile classes, run tests, and create build outputs.
- `./gradlew clean`: clear generated build artifacts.
- `./gradlew clean build`: full clean rebuild.
- `./gradlew tasks`: list available Gradle tasks.

## Coding Style & Naming Conventions
- Java 8 source compatibility (`sourceCompatibility = 1.8`) and UTF-8 source files.
- Use 4 spaces for indentation and keep line lengths readable (wrap long literals/log messages).
- Class names: `PascalCase` (`SureMClient`); methods/variables: `camelCase` (`sendMessage`, `messageId`).
- Constants: `UPPER_SNAKE_CASE` for static finals.
- Keep API call paths in `SureMServiceType` and request DTOs in `SureMModels` to reduce duplication.
- Prefer existing builder patterns (`SureMModels.MessageRequest.builder()`)
- Use explicit exception propagation (`throws IOException/Exception`) in sample methods for clarity.
- Formatting is manual; keep imports organized and remove unused code/comments before commit.

## Testing Guidelines
- JUnit 5 is configured in `build.gradle` (`junit-platform`, `junit-jupiter`).
- There are currently no committed tests, so add new tests under `src/test/java` when introducing business logic.
- Name tests with `*Test` (e.g., `SureMClientTest`) and keep one scenario per test method.
- Run `./gradlew test` before opening PRs that touch core behavior.

## Commit & Pull Request Guidelines
Recent commits follow a lightweight Conventional style (`feature:`, `chore:`), typically with lowercase type + `:` and short summary.
- Use clear, scoped commit messages, for example: `feature: add RCS sample payload`, `chore: update README examples`.
- PRs should include: purpose, changed files, any API contract impact, and run command evidence (`./gradlew test` or `./gradlew build`).

## Security & Configuration Tips
- Never commit real `USER_CODE`, `SECRET_KEY`, or phone numbers.
- Keep credentials in environment variables or local config outside git history.
- When adding samples, use placeholders like `{발신번호}` / `{수신번호}` and document required setup in the same PR.

## GitHub Metadata Optimization Tasks
- GitHub profile description (user: `suremapp`) should include: `SureM 공식 개발자 샘플 저장소입니다. 슈어엠 REST API, 문자 발송 API, SMS/LMS/MMS, 국제문자, 카카오 알림톡, RCS 연동 예제와 MCP 서버를 제공합니다.`
- Repository description for this repository: `SureM REST API Java sample for SMS, LMS, MMS and enterprise messaging integration.`
- Repository description for `surem-sms-mcp`: `SureM SMS MCP Server for Claude. Send SMS/LMS via SureM API using natural language.`
- Set GitHub topics:
  - For `SureM-Java-Sample`: `sms-api`, `lms-api`, `mms-api`, `messaging-api`, `korea-sms`, `enterprise-messaging`, `rest-api`, `java`, `surem`
  - For `surem-sms-mcp`: `mcp`, `model-context-protocol`, `claude`, `sms`, `lms`, `messaging`, `korea`, `surem`, `business-messaging`
- Apply these updates in GitHub repository settings (Profile → Edit profile, repository `Settings > General > About`).
