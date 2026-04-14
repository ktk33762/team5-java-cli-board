package com;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AppTest {
    @Test
    @DisplayName("닉네임 입력 받기")
    void t1() {
        String rs = AppTestRunner.run("""
                1
                용사
                종료
                """);
        assertThat(rs)
                .contains("닉네임을 입력하여 주세요!")
                .contains("닉네임 : ")
                .contains("용사");
    }

    @Test
    @DisplayName("슬라임과 전투 : 슬라임 처치")
    void t2() {
        String rs = AppTestRunner.run("""
                1
                용사
                전투
                1
                1
                종료
                """);
        assertThat(rs)
                .contains("야생에서 슬라임(이)가 나타났다!")
                .contains("어떻게 하시겠습니까?(숫자 입력)")
                .contains("1.전투 2.도망")
                .contains("용사(이)가 슬라임(을)를 공격")
                .contains("용사(이)가 슬라임(을)를 처치 성공");
    }

    @Test
    @DisplayName("슬라임과 전투 : 도망")
    void t3() {
        String rs = AppTestRunner.run("""
                1
                용사
                전투
                1
                2
                종료
                """);
        assertThat(rs)
                .contains("야생에서 슬라임(이)가 나타났다!")
                .contains("어떻게 하시겠습니까?(숫자 입력)")
                .contains("1.전투 2.도망")
                .contains("잽싸게 도망쳤습니다.");
    }

    @Test
    @DisplayName("정보 조회")
    void t4() {
        String rs = AppTestRunner.run("""
                1
                용사
                정보 조회
                종료
                """);
        assertThat(rs)
                .contains("닉네임 : 용사")
                .contains("직업 : Warrior")
                .contains("레벨 : 1")
                .contains("체력 : 30")
                .contains("공격력 : 3")
                .contains("방어력 : 2")
                .contains("경험치 : 0 / 10");
    }

    @Test
    @DisplayName("고블린 전투 : 고블린 처치")
    void t5() {
        String rs = AppTestRunner.run("""
                1
                용사
                전투
                1
                1
                전투
                2
                1
                종료
                """);
        assertThat(rs)
                .contains("야생에서 고블린(이)가 나타났다!")
                .contains("어떻게 하시겠습니까?(숫자 입력)")
                .contains("1.전투 2.도망")
                .contains("용사(이)가 고블린(을)를 공격")
                .contains("용사(이)가 고블린(을)를 처치 성공");
    }

    @Test
    @DisplayName("유저 사망 처리 : 마을에서 부활")
    void t6() {
        String rs = AppTestRunner.run("""
                1
                용사
                전투
                2
                1
                1
                종료
                """);
        assertThat(rs)
                .contains("용사(이)가 고블린 전투에서 전사하였습니다.")
                .contains("다음 중 선택하여 주세요.")
                .contains("1.마을에서 부활(경험치 패널티) 2.게임 재시작 3.게임 종료")
                .contains("용사(이)가 마을에서 부활하였습니다.(경험치 패널티)");
    }

    @Test
    @DisplayName("유저 사망 처리 : 게임 재시작")
    void t7() {
        String rs = AppTestRunner.run("""
                1
                용사
                전투
                2
                1
                2
                1
                용사2
                종료
                """);
        assertThat(rs)
                .contains("용사(이)가 고블린 전투에서 전사하였습니다.")
                .contains("다음 중 선택하여 주세요.")
                .contains("1.마을에서 부활(경험치 패널티) 2.게임 재시작 3.게임 종료")
                .contains("게임을 재시작합니다.")
                .contains("닉네임을 입력하여 주세요!")
                .contains("닉네임 : ")
                .contains("용사2");
    }

    @Test
    @DisplayName("유저 사망 처리 : 종료")
    void t8() {
        String rs = AppTestRunner.run("""
                1
                용사
                전투
                2
                1
                3
                """);
        assertThat(rs)
                .contains("용사(이)가 고블린 전투에서 전사하였습니다.")
                .contains("다음 중 선택하여 주세요.")
                .contains("1.마을에서 부활(경험치 패널티) 2.게임 재시작 3.게임 종료")
                .contains("게임을 종료합니다.");
    }

    @Test
    @DisplayName("마법사 캐릭터 생성")
    void t9() {
        String rs = AppTestRunner.run("""
                2
                용사
                종료
                """);
        assertThat(rs)
                .contains("마법사를 선택하셨습니다.")
                .contains("닉네임을 입력하여 주세요!")
                .contains("닉네임 : ")
                .contains("용사")
                .contains("Mage 용사(이)가 마을에 왔습니다.")
                .contains("게임을 종료합니다.");
    }
}
