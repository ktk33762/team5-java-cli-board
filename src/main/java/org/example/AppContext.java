package org.example;

import lombok.Getter;
import org.example.hunt.Hunt;
import org.example.store.Store;

import java.util.Scanner;

@Getter
public class AppContext {
    public static Scanner scanner;
    public static Character characterStatus;
    public static Hunt hunt;
    public static Store store;

    public static void renew() {
        scanner = new Scanner(System.in);
        characterStatus = new Character();
        hunt = new Hunt();
        store = new Store();
    }

    public static void clearConsole() {
        for (int i = 0; i < 15; ++i) {
            System.out.println();
        }
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
