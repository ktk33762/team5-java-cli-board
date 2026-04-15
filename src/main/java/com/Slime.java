package com;

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
}
