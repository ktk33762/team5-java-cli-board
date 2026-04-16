package org.example.store;

import lombok.Getter;

@Getter
public class Armor {
    String name;
    int armorNumber;
    int addDefense;
    int addHp;
    int needGold;

    Armor(int armorNumber) {
        this.armorNumber = armorNumber;
        switch (armorNumber) {
            case 1 -> {
                name = "천갑옷";
                addDefense = 5;
                addHp = 20;
                needGold = 10;
            }
            case 2 -> {
                name = "가죽갑옷";
                addDefense = 10;
                addHp = 50;
                needGold = 20;
            }
            case 3 -> {
                name = "철갑옷";
                addDefense = 30;
                addHp = 150;
                needGold = 60;
            }
            default -> {}
        }
    }

    void Description() {
        System.out.printf("%d. %s의 추가방어력 : %d \n", armorNumber, name, addDefense);
        System.out.printf("    %s의 추가체력 : %d \n", name, addHp);
        System.out.printf("    %s의 가격 : %d \n", name, needGold);
        System.out.println();
    }
}
