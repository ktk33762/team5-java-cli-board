package com;

public abstract class BaseUser extends BaseCharacter {
    protected int level;
    protected int exp;
    protected int curExp;

    public BaseUser(String name) {
        setName(name);
    }

    public abstract String getJob();

    public int getLevel() {
        return level;
    }

    protected abstract void setLevel(int level);

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
        if (curExp >= exp) {
            //SoundManager.getInstance().playSfxAsync(SoundType.SFX_LEVEL_UP);
            setLevel(level + 1);
            System.out.println("레벨업! 현재 레벨: " + level);
        }
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
        //SoundManager.getInstance().playSfx(SoundType.SFX_PLAYER_HIT);
    }
}

