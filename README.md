# 🗡️ CLI 텍스트 RPG

> Java CLI 기반 텍스트 RPG 게임입니다.  
> 직업을 선택하고, 몬스터를 처치하며, 레벨업하는 RPG 경험을 터미널에서 즐길 수 있습니다.

---

## 🎮 플레이 영상
> 소리 키면 조금 더 재밌습니다! 🔊

https://github.com/user-attachments/assets/76e47297-d24e-4126-83f1-3fb06c703585

---

## 🛠️ 기술 스택

| 항목 | 내용 |
|------|------|
| Language | Java 24 |
| Test | JUnit 5, AssertJ |
| Build | Gradle |

---

## 🚀 실행 방법

```bash
# 1. 저장소 클론

# 2. 실행
```
> ⚠️ 테스트 실행 시 사운드 재생으로 인해 시간이 다소 걸릴 수 있습니다.

---

## 📁 파일 구조

```
📦 src
 ┣ 📂 main
 ┃ ┗ 📂 java
 ┃   ┗ 📂 com
 ┃     ┣ 📂 controller             // UI 출력 및 사용자 흐름 제어 계층
 ┃     ┃ ┣ 📜 BattleController.java
 ┃     ┃ ┣ 📜 SystemController.java
 ┃     ┃ ┗ 📜 TownController.java
 ┃     ┃
 ┃     ┣ 📂 domain                 // 데이터 모델 및 엔티티 계층
 ┃     ┃ ┣ 📂 monster
 ┃     ┃ ┃ ┣ 📜 Goblin.java
 ┃     ┃ ┃ ┣ 📜 Monster.java
 ┃     ┃ ┃ ┗ 📜 Slime.java
 ┃     ┃ ┣ 📂 user
 ┃     ┃ ┃ ┣ 📜 BaseCharacter.java
 ┃     ┃ ┃ ┣ 📜 BaseUser.java
 ┃     ┃ ┃ ┣ 📜 Mage.java
 ┃     ┃ ┃ ┗ 📜 Warrior.java
 ┃     ┃ ┗ 📜 BaseInterface.java   // 도메인 공통 인터페이스
 ┃     ┃
 ┃     ┣ 📂 global                 // 전역 유틸리티 및 공통 클래스
 ┃     ┃ ┗ 📜 Rq.java              // CLI 입력 처리
 ┃     ┃
 ┃     ┣ 📂 service                // 핵심 비즈니스 로직 계층
 ┃     ┃ ┣ 📜 BattleService.java   // 전투 결과 계산 및 처리
 ┃     ┃ ┗ 📜 UserService.java     // 캐릭터 생성 및 사망 페널티 로직
 ┃     ┃
 ┃     ┣ 📂 sound                  // 오디오 재생 및 관리
 ┃     ┃ ┣ 📜 SoundFade.java
 ┃     ┃ ┣ 📜 SoundManager.java
 ┃     ┃ ┗ 📜 SoundType.java
 ┃     ┃
 ┃     ┣ 📜 App.java               // 애플리케이션 초기화 및 메인 루프
 ┃     ┣ 📜 AppContext.java  // 전역 상태 관리
 ┃     ┗ 📜 Main.java             // 프로그램 진입점 (Entry Point)
 ┃
 ┗ 📂 test
   ┗ 📂 java
     ┗ 📂 com
       ┣ 📂 service
       ┃ ┗ 📜 UserServiceTest.java // UserService 테스트 코드
       ┣ 📜 AppTest.java           // 게임 전체 흐름 테스트
       ┗ 📜 AppTestRunner.java     // 테스트용 콘솔 입출력 제어 러너
```

---

## ✅ 주요 기능
* **직업 및 캐릭터 시스템:** 전사(Warrior), 마법사(Mage) 중 원하는 직업을 선택하여 캐릭터를 생성할 수 있습니다.
* **전투 시스템:** 야생에서 슬라임, 고블린 등의 몬스터와 조우하며, 공격력과 방어력에 기반한 전투 로직이 적용됩니다.
* **성장 시스템:** 몬스터 처치 시 경험치를 획득하며, 일정 경험치 도달 시 레벨업하여 능력치가 상승합니다.
* **사망 패널티:** 전투 중 체력이 0이 되면 사망하며, 경험치 감소 패널티를 받고 마을에서 부활할 수 있습니다.
* **사운드 효과:** 배경음악(BGM)과 각종 효과음(SFX)이 적용되어 게임의 몰입도를 높입니다.

---

## 🏗️ 아키텍처 및 설계 포인트

* **도메인 순수화 및 다형성 (Domain & OCP)**
  * 캐릭터(`BaseUser`)와 몬스터(`Monster`)의 공통 상태를 상위 클래스(`BaseCharacter`)로 묶고, 피격(`takeDamage`) 로직을 인터페이스로 추출했습니다.
  * 도메인 객체 내부에서 `System.out.println` 등 UI 의존성을 제거하여 순수 데이터를 관리하도록 개선했습니다.
* **전역 상태 및 의존성 관리 (Global Context)**
  * `AppContext`를 전역 컨테이너로 활용하여 로그인된 유저 상태와 각 계층의 객체들을 중앙에서 관리합니다.
  * `Rq` 유틸리티 클래스를 도입하여 CLI 입력(`Scanner`) 처리를 모듈화했습니다.
* **비즈니스 로직 분리 (Service)**
  * 전투 흐름 제어, 캐릭터 생성, 경험치 부여 및 사망 패널티 등의 핵심 비즈니스 로직을 `UserService`와 `BattleService`로 분리하여 컨트롤러의 부담을 줄이고 응집도를 높였습니다.
 
---

## 💭 배운 점 / 회고

- TDD를 적용하면서 리팩토링 과정에서도 기존 테스트가 깨지지 않도록 유지하는 것의 중요성을 체감했습니다.
- `AppContext`를 통한 전역 상태 관리, `Rq`를 통한 입력 처리 분리 등의 패턴을 직접 적용해보며 이해도가 높아졌습니다.
- CSR 구조로 최대한 설계하여 역할에 맞게 분리하면서 웹 개발 방식을 이해하는데 도움이 되었습니다.
- 사운드 로직은 처음 다뤄봤는데, 멀티스레드와 페이드 인/아웃 효과까지 구현하면서 `ExecutorService`와 `volatile` 키워드의 필요성을 실감했습니다.
---
