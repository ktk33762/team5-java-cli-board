package com;

import com.sound.SoundManager;
import com.sound.SoundType;

public class Goblin extends Monster {
    public Goblin() {
        setName("고블린");
        setHp(20);
        setAttackPower(5);
        setDefensePower(2);
        setExpPoint(20);
    }

    @Override
    public String toString() {
        return """
                          ,      ,
                         /(.-""-.)\\
                     |\\  \\/      \\/  /|
                     | \\ / =.  .= \\ / |
                     \\( \\   o\\/o   / )/
                      \\_, '-/  \\-' ,_/
                        /   \\__/   \\
                        \\ \\__/\\__/ /
                      ___\\ \\|--|/ /___
                    /`    \\      /    `\\
                           고블린
                    """;
    }

    @Override
    public void playAppearSound() {
        //SoundManager.getInstance().playSfx(SoundType.SFX_GOBLIN_APPEAR);
    }

    @Override
    protected void playHitSound() {
        SoundManager.getInstance().playSfx(SoundType.SFX_GOBLIN_HIT);
    }
}
