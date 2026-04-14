package com;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AppTest {
    @Test
    @DisplayName("닉네임 입력 받기")
    void t1() {
        String rs = AppTestRunner.run("""
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
                용사
                전투
                1
                종료
                """);
        assertThat(rs)
                .contains("야생에서 슬라임 (이)가 나타났다!")
                .contains("어떻게 하시겠습니까?(숫자 입력)")
                .contains("1.전투 2.도망")
                .contains("용사(이)가 슬라임(을)를 공격")
                .contains("용사(이)가 슬라임(을)를 처치 성공");
    }

    @Test
    @DisplayName("슬라임과 전투 : 전투 중 유저 사망")
    void t3() {
        Warrior warrior = new Warrior("용사");
        warrior.setHp(1); // HP 1로 설정
        Slime slime = new Slime();
        BattleManager bm = new BattleManager(warrior, slime, new Scanner("1\n"));
        bm.start();
        assertThat(warrior.getDied()).isTrue();
    }

    @Test
    @DisplayName("슬라임과 전투 : 도망")
    void t4() {
        String rs = AppTestRunner.run("""
                용사
                전투
                2
                종료
                """);
        assertThat(rs)
                .contains("야생에서 슬라임 (이)가 나타났다!")
                .contains("어떻게 하시겠습니까?(숫자 입력)")
                .contains("1.전투 2.도망")
                .contains("잽싸게 도망쳤습니다.");
    }

    @Test
    @DisplayName("정보 조회")
    void t5() {
        String rs = AppTestRunner.run("""
                용사
                정보 조회
                종료
                """);
        assertThat(rs)
                .contains("닉네임 : 용사")
                .contains("레벨 : 1")
                .contains("체력 : 30");
    }
}
