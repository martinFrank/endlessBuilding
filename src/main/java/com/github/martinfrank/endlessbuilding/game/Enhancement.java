package com.github.martinfrank.endlessbuilding.game;

import com.github.martinfrank.endlessbuilding.mapdata.MapFieldType;

import java.util.ArrayList;
import java.util.List;

public class Enhancement {

    public static final Enhancement LUMBERMILL = createLumberMill();

    public final ResourecType resourecType;
    public final EnhancementType type;
    public final double basicIncomePerTick;
    public final MapFieldType mapFieldType;
    public final List<QualityUnit> basicCosts;
    public int level;

    public Enhancement(EnhancementType type, ResourecType resourecType, double basicIncomePerTick, MapFieldType mapFieldType, List<QualityUnit> basicCosts) {
        this.type = type;
        this.resourecType = resourecType;
        this.basicIncomePerTick = basicIncomePerTick;
        this.mapFieldType = mapFieldType;
        this.basicCosts = basicCosts;
        level = 1;
    }

    private static Enhancement createLumberMill() {
        QualityUnit qualityUnit = new QualityUnit(ResourecType.WOOD, 16);
        ArrayList<QualityUnit> qualityUnitList = new ArrayList<>();
        qualityUnitList.add(qualityUnit);
        return new Enhancement(EnhancementType.LUMBERMILL, ResourecType.WOOD, 0.25, MapFieldType.FORREST, qualityUnitList);
    }


}
