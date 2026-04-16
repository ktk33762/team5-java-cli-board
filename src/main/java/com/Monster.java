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
        System.out.printf("%s(이)가 %s(을)를 공격!   ", this.getName(), target.getName());
        target.takeDamage(this, attackPower);
    }

    @Override
    public void takeDamage(BaseCharacter attacker, int damage) {
        hp = Math.max(hp - Math.max(damage - defensePower, 1), 0);
        System.out.printf("%s 남은 HP : %d\n", this.getName(), this.getHp());
        //playHitSound();
        if (hp == 0) {
            System.out.printf("%s(이)가 %s(을)를 처치 성공!!\n", attacker.getName(), this.getName());
            System.out.println("획득한 경험치: " + expPoint);
            if (attacker instanceof BaseUser user) {
                user.addExp(expPoint);
            }
            System.out.println("마을로 돌아갑니다.");
        }
    }

    public void playAppearSound() {}
    protected void playHitSound() {}
}
