package com.service;

import com.domain.user.BaseUser;
import com.domain.user.Mage;
import com.domain.user.Warrior;

public class UserService {
    // 캐릭터 생성 로직
    public BaseUser createUser(String jobSelect, String name) {
        if (jobSelect.equals("1")) {
            return new Warrior(name);
        } else {
            return new Mage(name);
        }
    }

    public void levelUp(BaseUser user) {
        user.setLevel(user.getLevel() + 1);
    }

    // 사망 패널티 로직 (경험치 10% 감소 및 HP 회복)
    public void applyDeathPenalty(BaseUser user) {
        double penaltyExp = (double) user.getCurExp() * 0.9;
        user.setCurExp((int) penaltyExp);

        if (user instanceof Warrior warrior) {
            warrior.setHp(warrior.getLevel() * 30);
        } else if (user instanceof Mage mage) {
            mage.setHp(mage.getLevel() * 20);
        }
    }
}
