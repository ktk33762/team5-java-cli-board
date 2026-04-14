package com;

public class Monster extends BaseCharacter {
    protected int expPoint;

    public int getExpPoint() {
        return expPoint;
    }

    public void setExpPoint(int expPoint) {
        this.expPoint = expPoint;
    }

    @Override
    public void attack(BaseCharacter target) {
        target.takeDamage(this, attackPower);
    }

    @Override
    public void takeDamage(BaseCharacter attacker, int damage) {
        hp = Math.max(hp - Math.max(damage - defensePower, 1), 0);
        if(hp == 0) {
            System.out.printf("%s(이)가 %s (을)를 처치 성공\n", attacker.getName(), this.getName());
            if (attacker instanceof Warrior warrior) {
                warrior.addExp(expPoint);
            }
        }
    }
}
