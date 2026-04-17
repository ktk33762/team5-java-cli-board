package org.example.store;

import lombok.Getter;

@Getter
public class Weapon {
    String name;
    int weaponNumber;
    int addAttack;
    int needGold;

    Weapon(int weaponNumber) {
        this.weaponNumber = weaponNumber;
        switch (weaponNumber) {
            case 1 -> {
                name = "목검";
                addAttack = 10;
                needGold = 10;
            }
            case 2 -> {
                name = "철검";
                addAttack = 20;
                needGold = 20;
            }
            case 3 -> {
                name = "강철검";
                addAttack = 60;
                needGold = 60;
            }
            default -> {
            }
        }
    }

    void Description() {
        System.out.printf("%d. %s의 추가공격력 : %d \n", weaponNumber, name, addAttack);
        System.out.printf("    %s의 가격 : %d \n", name, needGold);
        System.out.println();
    }
}
