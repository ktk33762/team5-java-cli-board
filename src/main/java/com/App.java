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
        System.out.println(warrior.getName());
    }
}
