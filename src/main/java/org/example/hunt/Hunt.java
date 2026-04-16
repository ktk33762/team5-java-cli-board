package org.example.hunt;

import org.example.AppContext;
import org.example.Character;

public class Hunt {
    int setMonsterNumber;
    private final Monster monster;

    public Hunt() {
        monster = new Monster();
    }

    public void Run() {
        do {
            setMonsterNumber = AppContext.getResponse(Hunt::HuntingSelector);

            if (setMonsterNumber == 0)
                break;

            monster.MonsterSetting(setMonsterNumber);

            AppContext.clearConsole();
            HuntingHeader();
            monster.Status();
            Character.Status();

            while (Hunting()) {

            }
        } while (setMonsterNumber >= 1 && setMonsterNumber <= 3);
        AppContext.clearConsole();
    }

    static void HuntingSelector() {
        HuntingHeader();
        System.out.println("1. 고블린");
        System.out.println("2. 오크");
        System.out.println("3. 오우거");
        System.out.println("0. 뒤로가기");
    }

    static void HuntingHeader() {
        System.out.println("== 사냥터 ==");
    }

    static void SelectChoice() {
        System.out.println("1. 공격");
        System.out.println("0. 뒤로가기");
    }

    boolean Hunting() {
        switch (AppContext.getResponse(Hunt::SelectChoice)) {
            case 0 -> {
                AppContext.clearConsole();
                return false;
            }
            case 1 -> {
                Character.PlayerAttacked(monster);

                if (monster.hp <= 0)
                    monster.Reset();

                else
                    monster.Attack();

                System.out.println();
                HuntingHeader();
                monster.Status();
                Character.Status();
            }
            default -> {
                return true;
            }
        }
        return true;
    }
}
