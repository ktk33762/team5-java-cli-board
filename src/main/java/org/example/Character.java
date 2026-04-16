package org.example;

import lombok.Getter;
import lombok.Setter;
import org.example.hunt.Monster;
import org.example.store.Armor;
import org.example.store.Weapon;

@Getter
@Setter
public class Character {
    public static int attack;
    public static int defense;
    public static int gold;
    public static int hp;
    public static int maxHp;
    public static int level;
    public static int exp;
    public static int maxExp;
    public static Weapon weapon;
    public static Armor armor;

    public Character() {
        level = 1;
        attack = 10;
        defense = 10;
        gold = 10;
        maxHp = 100;
        hp = maxHp;
        exp = 50;
        maxExp = exp;
        weapon = null;
        armor = null;
    }

    public static int getAttack() {
        if (weapon != null)
            return weapon.getAddAttack() + attack;

        else
            return attack;
    }

    public static int getDefense() {
        if (armor != null)
            return armor.getAddDefense() + defense;

        else
            return defense;
    }

    public static int getMaxHp() {
        if (armor != null)
            return armor.getAddHp() + maxHp;

        else
            return maxHp;
    }

    public static int getHp() {
        if (armor != null)
            return armor.getAddHp() + hp;

        else
            return hp;
    }

    public static void Status() {
        System.out.printf("캐릭터의 레벨 : %d \n", level);
        System.out.printf("캐릭터의 공격력 : %d \n", attack);
        System.out.printf("캐릭터의 방어력 : %d \n", defense);
        System.out.printf("캐릭터의 골드 : %d \n", gold);
        System.out.printf("캐릭터의 체력 : %d / %d \n", hp, maxHp);
        System.out.printf("캐릭터의 경험치 : %d / %d \n", exp, maxExp);
        if (weapon != null)
            System.out.printf("무기의 추가 공격력 : %d \n", weapon.getAddAttack());
        if (armor != null) {
            System.out.printf("방어구의 추가 방어력 : %d \n", armor.getAddDefense());
            System.out.printf("방어구의 추가 체력 : %d \n", armor.getAddHp());
        }
    }

    public static void PlayerAttacked(Monster monster) {
        int damage = getAttack() - monster.getDefense();

        if (damage > 0) {
            System.out.printf("플레이어가 몬스터에게 %s의 데미지를 입혔습니다.\n", damage);
            monster.setHp(monster.getHp() - damage);
        } else
            System.out.println("몬스터가 방어에 성공했습니다.");

        if (monster.getHp() <= 0)
            MonsterKill(monster);
    }

    private static void MonsterKill(Monster monster) {
        exp += monster.getExp();
        gold += monster.getGold();
        System.out.printf("몬스터를 잡아 %s 경험치, %s 골드를 획득하였습니다.\n", exp, gold);

        if (exp >= maxExp)
            LevelUp();
    }

    private static void LevelUp() {
        System.out.println("플레이어가 레벨업했습니다.\n");
        maxHp += 50;
        hp = getMaxHp();
        level += 1;
        exp -= maxExp;
        maxExp += 50;
    }

    public static void PlayerDead() {
        System.out.println("플레이어가 사망했습니다. 프로그램을 종료합니다.");
        System.exit(0);
    }
}
