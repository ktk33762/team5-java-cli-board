package com;

public abstract class BaseCharacter implements BaseInterface {
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

    public void setDefensePower(int defensePower){
        this.defensePower = defensePower;
    }

    @Override
    public abstract void attack(BaseCharacter target);

    @Override
    public abstract void takeDamage(BaseCharacter attacker, int damage);
}
