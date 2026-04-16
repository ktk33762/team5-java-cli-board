package com.service;

import com.domain.user.BaseCharacter;
import com.domain.user.BaseUser;

public class BattleService {
    public void attack(BaseCharacter attacker, BaseCharacter target) {
        target.takeDamage(attacker.getAttackPower());
    }

    public boolean rewardExp(BaseUser user, int expPoint) {
        return user.addExp(expPoint);
    }
}
