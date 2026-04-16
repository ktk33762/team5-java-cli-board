package com.domain.user;

import com.sound.SoundManager;
import com.sound.SoundType;

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

    public abstract void setLevel(int level);

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

    public boolean addExp(int amount) {
        this.curExp += amount;
        return curExp >= exp;
    }

    @Override
    protected void playHitSound() {
        SoundManager.getInstance().playSfx(SoundType.SFX_PLAYER_HIT);
    }
}

