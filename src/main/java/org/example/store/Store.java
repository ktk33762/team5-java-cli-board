package org.example.store;

import lombok.Getter;
import lombok.Setter;
import org.example.AppContext;
import org.example.Character;

@Getter
@Setter
public class Store {
    static int setSelectNumber;

    public void Run() {
        do {
            setSelectNumber = AppContext.getResponse(Store::StoreSelector);

            if (setSelectNumber == 0)
                break;

            while (!Buying()) {
            }
        } while (setSelectNumber >= 1 && setSelectNumber <= 2);
        AppContext.clearConsole();
    }

    static void StoreSelector() {
        StoreHeader();
        System.out.println("1. 무기");
        System.out.println("2. 방어구");
        System.out.println("0. 뒤로가기");
    }

    static void StoreHeader() {
        System.out.println("== 상점 ==");
    }

    static void StoreItem() {
        System.out.println();
        System.out.printf("현재 캐릭터의 골드는: %d\n", Character.gold);
        switch (setSelectNumber) {
            case 1 -> {
                if (Character.weapon != null)
                    System.out.printf("현재 캐릭터의 무기는 : %s\n", Character.weapon.name);
                else
                    System.out.println("현재 캐릭터의 무기는 없습니다.");

                new Weapon(1).Description();
                new Weapon(2).Description();
                new Weapon(3).Description();
                System.out.println("0. 뒤로가기");

            }
            case 2 -> {
                if (Character.armor != null)
                    System.out.printf("현재 캐릭터의 방어구는 : %s", Character.armor);
                else
                    System.out.println("현재 캐릭터의 방어구는 없습니다.");

                new Armor(1).Description();
                new Armor(2).Description();
                new Armor(3).Description();
                System.out.println("0. 뒤로가기");
            }
            default -> {
            }
        }
    }

    boolean Buying() {
        int itemNumber = AppContext.getResponse(Store::StoreItem);

        switch (itemNumber) {
            case 0 -> {
                AppContext.clearConsole();
                return true;
            }
            case 1, 2, 3 -> {
                return Buying(itemNumber);
            }
            default -> {
                return false;
            }
        }
    }

    boolean Buying(int number) {
        switch (setSelectNumber) {
            case 1 -> {
                if (Character.weapon != null) {
                    if (Character.weapon.getWeaponNumber() >= number) {
                        System.out.println("지금 무기보다 약해서 구매할 필요가 없는 무기입니다.");
                        return false;
                    } else
                        return CanBuyWeapon(number);
                } else
                    return CanBuyWeapon(number);
            }

            case 2 -> {
                if (Character.armor != null) {
                    if (Character.armor.getArmorNumber() <= number) {
                        System.out.println("지금 방어구보다 튼튼하지 못해서 구매할 필요가 없는 방어구입니다.");
                        return false;
                    } else
                        return CanBuyArmor(number);
                } else
                    return CanBuyArmor(number);
            }
            default -> {
                return false;
            }
        }
    }

    boolean CanBuyWeapon(int number) {
        Weapon item = new Weapon(number);
        if (Character.gold < item.needGold) {
            System.out.println("골드가 부족해서 구매할 수 없습니다.");
            return false;
        } else {
            System.out.printf("%s를 구매완료했습니다.\n", item.getName());
            System.out.println();
            Character.gold -= item.needGold;
            Character.weapon = item;
            return true;
        }
    }

    boolean CanBuyArmor(int number) {
        Armor item = new Armor(number);
        if (Character.gold < item.needGold) {
            System.out.println("골드가 부족해서 구매할 수 없습니다.");
            return false;
        } else {
            System.out.printf("%s를 구매완료했습니다.", item.getName());
            System.out.println();
            Character.gold -= item.needGold;
            Character.armor = item;
            return true;
        }
    }
}
