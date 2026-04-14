package com;

import java.util.Scanner;

public class BattleManager {
    private final BaseCharacter user;
    private final BaseCharacter monster;
    private final Scanner scanner;

    public BattleManager(BaseCharacter user, BaseCharacter monster, Scanner scanner) {
        this.user = user;
        this.monster = monster;
        this.scanner = scanner;
    }

    public void start() {
        System.out.printf("야생에서 %s (이)가 나타났다!.\n어떻게 하시겠습니까?(숫자 입력)\n 1.전투 2.도망\n", monster.getName());
        System.out.print("명령) ");
        String select = scanner.nextLine().trim();
        if(select.equals("1")) {
            while(!user.getDied() && !monster.getDied()) {
                user.attack(monster);
                if(monster.getDied()) break;
                monster.attack(user);
                if(user.getDied()) break;
            }
        }
        else if(select.equals("2")) {
            System.out.println("도망쳤습니다.");
        }
        else {
            System.out.println("다시 입력해주세요");
            start();
        }
    }
}
