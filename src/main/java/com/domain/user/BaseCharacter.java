package com.domain.user;

import com.domain.BaseInterface;

public class BaseCharacter implements BaseInterface {
    protected String name;
    protected int hp;
    protected int attackPower;
    protected int defensePower;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public int getDefensePower() {
        return defensePower;
    }

    public void setDefensePower(int defensePower) {
        this.defensePower = defensePower;
    }

    public boolean getDied() {
        return hp == 0;
    }

    @Override
    public void takeDamage(int attackPower) {
        int actualDamage = Math.max(attackPower - this.defensePower, 1);
        this.hp = Math.max(this.hp - actualDamage, 0);
        System.out.printf("%s 남은 HP : %d\n", name, hp);
        playHitSound();
    }

    protected void playHitSound() {}
}
