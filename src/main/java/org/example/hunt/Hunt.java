package org.example.hunt;

import org.example.AppContext;

public class Hunt {
    int setMonsterNumber;
    int monsterAttack;
    int monsterShild;
    int monsterHP;
    int monsterMaxHP;

    public void run() {

        do {
            setMonsterNumber = AppContext.getResponse(Hunt::huntingSelector);

            if (setMonsterNumber == 0) return;
        } while (setMonsterNumber < 0 || setMonsterNumber > 3);

        monsterSetting();

        while(true)
        {
            if (hunting() == 0)
                return;
        }
    }

    static void huntingSelector() {
        AppContext.clearConsole();

        huntingHeader();
        System.out.println("1. 고블린");
        System.out.println("2. 오크");
        System.out.println("3. 오우거");
        System.out.println("0. 뒤로가기");
    }

    static void huntingHeader()
    {
        System.out.println("== 사냥터 ==");
    }

    void monsterSetting() {
        switch (setMonsterNumber) {
            case 1 -> {
                monsterAttack = 15;
                monsterShild = 5;
                monsterHP = 10;
                monsterMaxHP = monsterHP;
            }
            case 2 -> {
                monsterAttack = 25;
                monsterShild = 15;
                monsterHP = 20;
                monsterMaxHP = monsterHP;
            }
            case 3 -> {
                monsterAttack = 50;
                monsterShild = 30;
                monsterHP = 50;
                monsterMaxHP = monsterHP;
            }
        }
    }

    void monsterStatus(){
        AppContext.clearConsole();

        System.out.printf("몬스터의 공격력 : %s \n", monsterAttack);
        System.out.printf("몬스터의 방어력 : %s \n", monsterShild);
        System.out.printf("캐릭터의 체력 : %s / %s \n", monsterHP, monsterMaxHP);
    }

    void selectChoice()
    {
        System.out.println();
    }

    int hunting() {
        huntingHeader();
        monsterStatus();
        AppContext.defalutStatus();
        selectChoice();

        int response = AppContext.getResponse(Hunt::huntingSelector);

        if (response > 0 )
        {

        }

        return response;
    }
}
