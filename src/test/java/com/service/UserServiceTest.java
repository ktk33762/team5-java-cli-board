package com.service;

import com.domain.user.BaseUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserServiceTest {
    @Test
    @DisplayName("캐릭터 생성 서비스 - 1번을 누르면 전사가 생성되어야 한다")
    void t1() {
        UserService userService = new UserService();
        BaseUser user = userService.createUser("1", "용사");

        assertThat(user.getName()).isEqualTo("용사");
        assertThat(user.getJob()).isEqualTo("전사");
    }

    @Test
    @DisplayName("사망 패널티(전사) - 경험치가 10% 감소하고 체력이 회복되어야 한다")
    void t2() {
        UserService userService = new UserService();
        BaseUser user = userService.createUser("1", "용사");

        user.setCurExp(10); // 임의로 사냥을 통해 경험치 10을 얻었다고 가정
        user.setHp(0);      // 사망

        userService.applyDeathPenalty(user);

        assertThat(user.getCurExp()).isEqualTo(9); // 10의 10%가 깎여 9가 되어야 함
        assertThat(user.getHp()).isEqualTo(30);    // 전사 1레벨 기준 HP 30으로 부활해야 함
    }

    @Test
    @DisplayName("사망 패널티(마법사) - 경험치가 10% 감소하고 체력이 회복되어야 한다")
    void t3() {
        UserService userService = new UserService();
        BaseUser user = userService.createUser("2", "용사");

        user.setCurExp(10); // 임의로 사냥을 통해 경험치 10을 얻었다고 가정
        user.setHp(0);      // 사망

        userService.applyDeathPenalty(user);

        assertThat(user.getCurExp()).isEqualTo(9); // 10의 10%가 깎여 9가 되어야 함
        assertThat(user.getHp()).isEqualTo(20);    // 전사 1레벨 기준 HP 30으로 부활해야 함
    }

    @Test
    @DisplayName("레벨업")
    void t4() {
        UserService userService = new UserService();
        BaseUser user = userService.createUser("1", "용사");

        userService.levelUp(user);
        assertThat(user.getLevel()).isEqualTo(2);
        assertThat(user.getCurExp()).isEqualTo(0);
    }
}
