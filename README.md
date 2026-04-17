# team5-java-cli-board

# 📋 CLI Text Board

![Java](https://img.shields.io/badge/Java-17+-orange?logo=openjdk&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-8.x-02303A?logo=gradle&logoColor=white)
![JUnit5](https://img.shields.io/badge/Test-JUnit5-25A162?logo=junit5&logoColor=white)
![AssertJ](https://img.shields.io/badge/Assert-AssertJ-blue)
![TDD](https://img.shields.io/badge/methodology-TDD-brightgreen)

> 터미널에서 동작하는 Java CLI 게시판 프로젝트입니다.
> **TDD(테스트 주도 개발)** 방법론으로 Red → Green → Refactor 사이클을 준수하며 개발했습니다.

---
## 🚀 실행 방법

```bash
# 1. 저장소 클론
git clone [https://github.com/your-repo/team5-java-cli-board.git](https://github.com/your-repo/team5-java-cli-board.git)
cd team5-java-cli-board

# 2. 빌드
./gradlew build

# 3. 실행
./gradlew run
```
---
## 🧪테스트 실행
```Bash
./gradlew test
```
전체 테스트 통과 후 build/reports/tests/test/index.html에서 결과를 확인할 수 있습니다.
---
## 🏗 프로젝트 구조
```text
cli-text-board/
├── build.gradle
├── src/
│   ├── main/java/org/example/
│   │   ├── Main.java               # 진입점
│   │   ├── App.java                # 콘솔 I/O + 명령어 라우팅
│   │   ├── Article.java            # 게시글 도메인 객체
│   │   ├── ArticleRepository.java  # 데이터 저장/조회/삭제
│   │   ├── ArticleService.java     # 비즈니스 로직
│   │   └── Rq.java                 # 명령어 파싱
│   └── test/java/org/example/
│       ├── ArticleTest.java
│       ├── ArticleRepositoryTest.java
│       ├── ArticleServiceTest.java
│       ├── RqTest.java
│       └── AppIntegrationTest.java
└── README.md
```
___

## 🧱 클래스 책임 분리

| 클래스 | 책임 |
|---|---|
| Article | 게시글 데이터 표현 |
| ArticleRepository | ArrayList 기반 CRUD |
| ArticleService | 비즈니스 로직 (최신순 정렬 등) |
| Rq | 입력 문자열 파싱 |
| App | 콘솔 I/O + 명령어 라우팅 |
| Main | 의존성 조립 및 실행 진입점 |
___
## 🛠 기술 스택
Language: Java 17+

Build: Gradle

Test: JUnit 5, AssertJ

Storage: In-memory (ArrayList)

---
## 📌 버전 히스토리

| 태그 | 내용 |
|---|---|
| v0.1.0-article | Article 도메인 클래스 구현 |
| v0.2.0-rq | Rq 명령어 파서 구현 |
| v0.3.0-repository | ArticleRepository 구현 |
| v0.4.0-service | ArticleService 비즈니스 로직 구현 |
| v0.5.0-app | App 콘솔 I/O + 라우팅 구현 |
| v1.0.0 | Main 진입점 연결, 전체 완성 |
| v1.1.0 | help 명령어 추가 |

