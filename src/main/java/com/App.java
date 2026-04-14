package com;

import java.util.Scanner;

public class App {
    private final Scanner scanner;

    public App(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        System.out.println("CLI 기반 텍스트 RPG");
        System.out.println("여러분은 이제 모험의 세계로 떠납니다.");
        System.out.println("닉네임을 입력하여 주세요!");
        System.out.print("닉네임 : ");
        String name = scanner.nextLine();
        Warrior warrior = new Warrior(name);
        System.out.printf("닉네임 설정 완료! %s\n",warrior.getName());
        while(true) {
            System.out.println("종료, 전투, 정보 조회 중 선택하여주세요.");
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();
            switch (cmd) {
                case "종료" -> {
                    System.out.println("게임을 종료합니다.");
                    return;
                }
                case "전투" -> {
                    BattleManager bm = new BattleManager(warrior, new Slime(), scanner);
                    bm.start();
                }
                case "정보 조회" -> {
                    System.out.println("정보");
                    System.out.println("------------------");
                    System.out.println("닉네임 : " + warrior.getName());
                    System.out.println("레벨 : " + warrior.getLevel());
                    System.out.printf("체력 : %d\n", warrior.getHp());
                    System.out.printf("공격력 : %d\n", warrior.getAttackPower());
                    System.out.printf("방어력 : %d\n", warrior.getDefensePower());
                    System.out.printf("경험치 : %d / %d\n", warrior.getCurExp(), warrior.getExp());
                    System.out.println("------------------");
                }
                default -> System.out.println("다시 입력해주세요.");
            }
        }
    }
}
