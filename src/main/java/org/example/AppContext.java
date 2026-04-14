package org.example;

import org.example.hunt.Hunt;

import java.util.Scanner;

public class AppContext {
    public static int attack;
    public static int defense;
    public static int gold;
    public static int HP;
    public static int MaxHP;
    public static Scanner scanner;
    public static Hunt hunt;

    public static void renew() {
        attack = 10;
        defense = 10;
        gold = 10;
        MaxHP = 100;
        HP = MaxHP;
        scanner = new Scanner(System.in);
        hunt = new Hunt();
    }

    public static Hunt getHunt() {
        return hunt;
    }

    public static void clearConsole() {
        for (int i = 0; i < 15; ++i) {
            System.out.println();
        }
    }

    public static void defalutStatus() {
        System.out.printf("캐릭터의 공격력 : %s \n", attack);
        System.out.printf("캐릭터의 방어력 : %s \n", defense);
        System.out.printf("캐릭터의 골드 : %s \n", gold);
        System.out.printf("캐릭터의 체력 : %s / %s \n", HP, MaxHP);
    }

    public static void choiceOfPlace() {
        System.out.println("1. 사냥터");
        System.out.println("2. 상점");
        System.out.println("3. 장비");
        System.out.println("4. 인벤토리");
        System.out.println("5. 종료");
    }

    public static int getResponse(Runnable action) {
        action.run();
        System.out.print("번호를 입력하세요 : ");
        String cmd = scanner.nextLine();

        while (true) {
            try {
                return Integer.parseInt(cmd);
            } catch (NumberFormatException e) {
                clearConsole();
                System.out.println("번호를 다시 입력하세요. ");
                cmd = scanner.nextLine();
            }
        }
    }
}
