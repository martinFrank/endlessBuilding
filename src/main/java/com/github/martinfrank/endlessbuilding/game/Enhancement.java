package com.github.martinfrank.endlessbuilding.game;

import com.github.martinfrank.endlessbuilding.mapdata.MapFieldType;

import java.util.ArrayList;
import java.util.List;

public class Enhancement {

    public static final Enhancement LUMBERMILL = createLumberMill();

    public final ResourecType resourecType;
    public final double basicIncomePerTick;
    public final MapFieldType mapFieldType;
    public final List<Price> basicCosts;
    public int level;

    public Enhancement(ResourecType resourecType, double basicIncomePerTick, MapFieldType mapFieldType, List<Price> basicCosts) {
        this.resourecType = resourecType;
        this.basicIncomePerTick = basicIncomePerTick;
        this.mapFieldType = mapFieldType;
        this.basicCosts = basicCosts;
        level = 1;
    }

    private static Enhancement createLumberMill() {
        Price price = new Price(ResourecType.WOOD, 16);
        ArrayList<Price> priceList = new ArrayList<>();
        priceList.add(price);
        return new Enhancement(ResourecType.WOOD, 0.25, MapFieldType.FORREST, priceList);
    }


}
