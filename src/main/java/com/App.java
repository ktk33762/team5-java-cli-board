package com;

import java.util.Scanner;

public class App {
    private final Scanner scanner;

    public App(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        while (true) {
            System.out.println("CLI 기반 텍스트 RPG");
            System.out.println("여러분은 이제 모험의 세계로 떠납니다.");
            System.out.println("====================================");
            System.out.println("직업을 선택하여 주세요!");
            System.out.println("1.전사 2.마법사");
            System.out.print("숫자를 입력하여 주세요 -> ");
            String select = scanner.nextLine().trim();
            switch (select) {
                case "1" -> System.out.println("전사를 선택하셨습니다.");
                case "2" -> System.out.println("마법사를 선택하셨습니다.");
                default -> {
                    System.out.println("다시 입력해주세요.");
                    continue;
                }
            }
            System.out.println("닉네임을 입력하여 주세요!");
            System.out.print("닉네임 : ");
            String name = scanner.nextLine().trim();
            BaseUser user = (select.equals("1")) ? new Warrior(name) : new Mage(name);
            System.out.printf("닉네임 설정 완료! %s\n", user.getName());
            System.out.println("====================================");
            System.out.printf("%s %s(이)가 마을에 왔습니다.\n", user.getJob(), user.getName());
            boolean isGameOver = false;
            while (!isGameOver) {
                System.out.println("종료, 전투, 정보 조회 중 선택하여주세요.");
                System.out.print("명령) ");
                String cmd = scanner.nextLine().trim();
                switch (cmd) {
                    case "종료" -> {
                        System.out.println("게임을 종료합니다.");
                        return;
                    }
                    case "전투" -> {
                        while (true) {
                            System.out.println("어떤 몬스터와 전투하겠습니까?");
                            System.out.println("1.슬라임 2.고블린 3.뒤로 가기");
                            System.out.print("숫자를 입력하여 주세요 -> ");
                            String num = scanner.nextLine().trim();
                            if (num.equals("1") || num.equals("2")) {
                                Monster monster = num.equals("1") ? new Slime() : new Goblin();
                                BattleManager bm = new BattleManager(user, monster, scanner);
                                System.out.println("전투를 시작합니다.");
                                int rs = bm.start();
                                if (rs == 2) {
                                    System.out.println("게임을 재시작합니다.");
                                    isGameOver = true;
                                } else if (rs == 3) {
                                    System.out.println("게임을 종료합니다.");
                                    return;
                                }
                                break;
                            } else if (num.equals("3")) {
                                System.out.println("이전 메뉴로 돌아갑니다.");
                                break;
                            } else {
                                System.out.println("다시 입력해주세요.");
                            }
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
    }
}
