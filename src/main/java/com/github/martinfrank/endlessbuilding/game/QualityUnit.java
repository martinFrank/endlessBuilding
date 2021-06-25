package com.github.martinfrank.endlessbuilding.game;

public class QualityUnit { //deutsch: Mengeneinheit

    public final ResourecType unit;
    public final double amount;

    public QualityUnit(ResourecType unit, double amount) {
        this.unit = unit;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "QualityUnit{" +
                "unit=" + unit +
                ", amount=" + amount +
                '}';
    }


}
