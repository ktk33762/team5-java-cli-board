package com;

import com.sound.SoundManager;
import com.sound.SoundType;

public class Slime extends Monster {
    public Slime() {
        setName("슬라임");
        setHp(10);
        setAttackPower(3);
        setDefensePower(1);
        setExpPoint(10);
    }

    @Override
    public String toString() {
        return """
                         _______
                       /         \\
                      |  o     o  |
                      |     ^     |
                      |   \\___/   |
                       \\_________/
                          슬라임
                    """;
    }

    @Override
    public void playAppearSound() {
        //SoundManager.getInstance().playSfx(SoundType.SFX_SLIME_APPEAR);
    }

    @Override
    protected void playHitSound() {
        SoundManager.getInstance().playSfx(SoundType.SFX_SLIME_HIT);
    }
}
