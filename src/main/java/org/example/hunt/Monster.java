package org.example.hunt;

import lombok.Getter;
import lombok.Setter;
import org.example.Character;

@Getter
@Setter
public class Monster {
    int attack;
    int defense;
    int hp;
    int maxHp;
    int exp;
    int gold;

    void MonsterSetting(int setMonsterNumber) {
        switch (setMonsterNumber) {
            case 1 -> {
                attack = 15;
                defense = 5;
                hp = 10;
                maxHp = hp;
                exp = 5;
                gold = 10;
            }
            case 2 -> {
                attack = 25;
                defense = 15;
                hp = 20;
                maxHp = hp;
                exp = 20;
                gold = 40;
            }
            case 3 -> {
                attack = 50;
                defense = 30;
                hp = 50;
                maxHp = hp;
                exp = 50;
                gold = 100;
            }
            default -> {}
        }
    }

    void Status() {
        System.out.printf("몬스터의 공격력 : %s \n", attack);
        System.out.printf("몬스터의 방어력 : %s \n", defense);
        System.out.printf("몬스터의 체력 : %s / %s \n", hp, maxHp);
        System.out.println();
    }

    void Reset() {
        System.out.printf("몬스터가 사망했습니다.\n%s의 경험치와 %s의 골드를 획득하셨습니다\n", exp, gold);
        hp = maxHp;
    }

    void Attack() {
        if (Character.getHp() + Character.getDefense() <= attack)
            Character.PlayerDead();

        else {
            if (Character.getDefense() < attack) {
                int damage = attack - Character.getDefense();
                System.out.printf("플레이어가 %s만큼의 데미지를 입었습니다.\n", damage);
                Character.hp -= damage;
            }

            else
                System.out.println("플레이어가 방어에 성공합니다.");
        }
    }
}
