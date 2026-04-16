package com.domain.user;

public class Mage extends BaseUser {
    public Mage(String name) {
        super(name);
        setLevel(1);
    }

    @Override
    public String getJob() {
        return "마법사";
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
        setCurExp(0);
        setExp(level * 10);
        setHp(level * 20);
        setAttackPower(level * 4);
        setDefensePower(level);
    }
}
