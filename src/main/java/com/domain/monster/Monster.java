package com.domain.monster;

import com.domain.user.BaseCharacter;

public class Monster extends BaseCharacter {
    protected int expPoint;

    public int getExpPoint() {
        return expPoint;
    }

    public void setExpPoint(int expPoint) {
        this.expPoint = expPoint;
    }

    @Override
    public void takeDamage(int attackPower) {
        super.takeDamage(attackPower);
    }

    public void playAppearSound() {}
}
