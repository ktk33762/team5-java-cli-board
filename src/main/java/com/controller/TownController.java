package com.controller;

import com.AppContext;
import com.domain.monster.Goblin;
import com.domain.monster.Monster;
import com.domain.monster.Slime;
import com.domain.user.BaseUser;
import com.global.Rq;
import com.sound.SoundManager;
import com.sound.SoundType;

public class TownController {
    public int start() {
        BaseUser user = AppContext.user;
        while (true) {
            System.out.println("종료, 전투, 정보 조회 중 선택하여주세요.");
            System.out.print("명령) ");
            String cmd = Rq.getLine();
            switch (cmd) {
                case "종료" -> {
                    System.out.println("게임을 종료합니다.");
                    return -1;
                }
                case "전투" -> {
                    int rs = handleBattleMenu();
                    SoundManager.getInstance().playBgm(SoundType.BGM_TOWN);
                    if (rs == 2) {
                        return 1;   // 재시작을 위해 SystemController 로 루프 반환
                    } else if (rs == 3) {
                        return -1;  // 시스템 완전 종료
                    }
                }
                case "정보 조회" -> {
                    System.out.println("====정보====");
                    System.out.println("------------------");
                    System.out.println("닉네임 : " + user.getName());
                    System.out.println("직업 : " + user.getJob());
                    System.out.println("레벨 : " + user.getLevel());
                    System.out.printf("체력 : %d\n", user.getHp());
                    System.out.printf("공격력 : %d\n", user.getAttackPower());
                    System.out.printf("방어력 : %d\n", user.getDefensePower());
                    System.out.printf("경험치 : %d / %d\n", user.getCurExp(), user.getExp());
                    System.out.println("------------------");
                }
                default -> System.out.println("다시 입력해주세요.");
            }
        }
    }

    private int handleBattleMenu() {
        while (true) {
            SoundManager.getInstance().playBgm(SoundType.BGM_BATTLE);
            System.out.println("어떤 몬스터와 전투하겠습니까?");
            System.out.println("1.슬라임 2.고블린 3.뒤로 가기");
            System.out.print("숫자를 입력하여 주세요 -> ");
            String num = Rq.getLine();
            if (num.equals("1") || num.equals("2")) {
                Monster monster = num.equals("1") ? new Slime() : new Goblin();
                BattleController bm = new BattleController(monster);
                System.out.println("전투를 시작합니다.");
                int rs = bm.start();
                if (rs == 2) {
                    System.out.println("게임을 재시작합니다.");
                    return 2;
                } else if (rs == 3) {
                    System.out.println("게임을 종료합니다.");
                    return 3;
                }
                return 1; // 전투 끝 (승리/도망 등), 마을 메뉴로 복귀

            } else if (num.equals("3")) {
                System.out.println("이전 메뉴로 돌아갑니다.");
                return 1; // 마을 메뉴로 복귀
            } else {
                System.out.println("다시 입력해주세요.");
            }
        }
    }
}
