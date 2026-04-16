package com.domain.user;

public class Warrior extends BaseUser {
    public Warrior(String name) {
        super(name);
        setLevel(1);
    }

    @Override
    public String getJob() {
        return "전사";
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
        setCurExp(0);
        setExp(level * 10);
        setHp(level * 30);
        setAttackPower(level * 3);
        setDefensePower(level * 2);
    }
}
