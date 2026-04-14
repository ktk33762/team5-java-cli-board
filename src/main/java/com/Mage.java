package com;

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
    protected void setLevel(int level) {
        this.level = level;
        setCurExp(0);
        setExp(level * 10);
        setHp(level * 20);
        setAttackPower(level * 4);
        setDefensePower(level);
    }
}
