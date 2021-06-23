package com.github.martinfrank.endlessbuilding.game;

public class Price {

    public final ResourecType unit;
    public final double amount;

    public Price(ResourecType unit, double amount) {
        this.unit = unit;
        this.amount = amount;
    }
}
