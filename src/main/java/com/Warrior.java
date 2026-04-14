package com;

public class Warrior extends BaseCharacter {
    protected int level;
    protected int exp;
    protected int curExp;

    public Warrior(String name) {
        setName(name);
        setLevel(1);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        setCurExp(0);
        setExp(level * 10);
        setHp(level * 30);
        setAttackPower(level * 3);
        setDefensePower(level * 2);
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getCurExp() {
        return curExp;
    }

    public void setCurExp(int curExp) {
        this.curExp = curExp;
    }

    public void addExp(int amount) {
        this.curExp += amount;
        if(curExp >= exp) {
            setLevel(level + 1);
        }
    }

    public boolean getDied() {
        return hp <= 0;
    }

    @Override
    public void attack(BaseCharacter target){
        target.takeDamage(this, attackPower);
    }

    @Override
    public void takeDamage(BaseCharacter attacker, int damage){
        hp = Math.max(hp - Math.max(damage - defensePower, 1), 0);
        if(hp == 0) {
            System.out.println("게임 오버");
            // 게임 오버 처리
            // 재시작, 종료 선택 분기 처리
        }
    }
}
