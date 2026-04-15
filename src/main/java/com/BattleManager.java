package com;

import java.util.Scanner;

public class BattleManager {
    private final BaseCharacter user;
    private final Monster monster;
    private final Scanner scanner;

    public BattleManager(BaseCharacter user, Monster monster, Scanner scanner) {
        this.user = user;
        this.monster = monster;
        this.scanner = scanner;
    }

    public int start() {
        System.out.println(monster);
        monster.playAppearSound();
        System.out.printf("야생에서 %s(이)가 나타났다!.\n어떻게 하시겠습니까?(숫자 입력)\n 1.전투 2.도망\n", monster.getName());
        System.out.print("숫자를 입력하여 주세요. -> ");
        String select = scanner.nextLine().trim();
        if (select.equals("1")) {
            while (!user.getDied() && !monster.getDied()) {
                user.attack(monster);
                if (monster.getDied()) break;
                monster.attack(user);
                if (user.getDied()) break;
            }
            if (user.getDied()) {
                return gameOver(monster);
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
        String num = scanner.nextLine().trim();
        switch (num) {
            case "1" -> {
                if (user instanceof Warrior warrior) {
                    double penaltyExp = (double) warrior.getCurExp() * 0.9;
                    warrior.setCurExp((int) penaltyExp);
                    warrior.setHp(warrior.getLevel() * 30);
                    System.out.printf("%s(이)가 마을에서 부활하였습니다.(경험치 패널티)\n", warrior.getName());
                }
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
