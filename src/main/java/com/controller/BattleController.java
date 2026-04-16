package com.controller;

import com.AppContext;
import com.domain.monster.Monster;
import com.domain.user.BaseCharacter;
import com.domain.user.BaseUser;
import com.global.Rq;
import com.sound.SoundManager;
import com.sound.SoundType;

public class BattleController {
    private final Monster monster;
    private final BaseUser user;

    public BattleController(Monster monster) {
        this.monster = monster;
        user = AppContext.user;
    }

    public int start() {
        System.out.println(monster);
        monster.playAppearSound();
        System.out.printf("야생에서 %s(이)가 나타났다!\n어떻게 하시겠습니까?(숫자 입력)\n 1.전투 2.도망\n", monster.getName());
        System.out.print("숫자를 입력하여 주세요. -> ");
        String select = Rq.getLine();
        if (select.equals("1")) {
            while (!user.getDied() && !monster.getDied()) {
                System.out.printf("%s(이)가 %s(을)를 공격!   ", user.getName(), monster.getName());
                AppContext.battleService.attack(user, monster);

                if (monster.getDied()) {
                    System.out.printf("%s(이)가 %s(을)를 처치 성공!!\n", user.getName(), monster.getName());
                    System.out.println("획득한 경험치: " + monster.getExpPoint());

                    boolean leveledUp = AppContext.battleService.rewardExp(user, monster.getExpPoint());
                    if (leveledUp) {
                        AppContext.userService.levelUp(user);
                        System.out.println("레벨업! 현재 레벨: " + user.getLevel());
                        SoundManager.getInstance().playSfx(SoundType.SFX_LEVEL_UP);
                    }
                    System.out.println("마을로 돌아갑니다.");
                    break;
                }
                System.out.printf("%s(이)가 %s(을)를 공격!   ", monster.getName(), user.getName());
                AppContext.battleService.attack(monster, user);

                if (user.getDied()) {
                    return gameOver(monster);
                }
            }
        } else if (select.equals("2")) {
            System.out.println("잽싸게 도망쳤습니다.");
        } else {
            System.out.println("다시 입력해주세요");
            return start();
        }
        return 1;
    }

    private int gameOver(BaseCharacter attacker) {
        System.out.printf("%s(이)가 %s 전투에서 전사하였습니다.\n", user.getName(), attacker.getName());
        System.out.println("다음 중 선택하여 주세요.");
        System.out.println("1.마을에서 부활(경험치 패널티) 2.게임 재시작 3.게임 종료");
        System.out.print("숫자를 입력하여 주세요. -> ");
        String num = Rq.getLine();
        switch (num) {
            case "1" -> {
                AppContext.userService.applyDeathPenalty(user);
                System.out.printf("%s(이)가 마을에서 부활하였습니다.(경험치 패널티)\n", user.getName());
                return 1;
            }
            case "2" -> {
                return 2;
            }
            case "3" -> {
                return 3;
            }
            default -> {
                System.out.println("다시 입력해주세요.");
                return gameOver(attacker);
            }
        }
    }
}
