package com.github.martinfrank.endlessbuilding.game;

import com.github.martinfrank.endlessbuilding.mapdata.MapFieldType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Enhancement {

    public static final Enhancement LUMBERMILL = createLumberMill();

    public final EnhancementType type;
    public final List<QualityUnit> basicIncome;
    public final MapFieldType mapFieldType;
    public final List<QualityUnit> basicCosts;
    public final List<QualityUnit> basicUpkeep;
    public int level;
    public double efficency; // in percent: 0...1

    public Enhancement(EnhancementType type, MapFieldType mapFieldType, List<QualityUnit> basicIncome, List<QualityUnit> basicCosts, List<QualityUnit> basicUpkeep) {
        this.type = type;
        this.mapFieldType = mapFieldType;
        this.basicIncome = basicIncome;
        this.basicCosts = basicCosts;
        this.basicUpkeep = basicUpkeep;
        level = 0;
        efficency = 0;
    }

    private static Enhancement createLumberMill() {
        QualityUnit costWood = new QualityUnit(ResourecType.WOOD, 16);
        QualityUnit costStone = new QualityUnit(ResourecType.STONE, 16);
        List<QualityUnit> costs = new ArrayList<>();
        costs.add(costWood);
        costs.add(costStone);

        QualityUnit incomeWood = new QualityUnit(ResourecType.WOOD, 0.1);
        List<QualityUnit> incomes = new ArrayList<>();
        incomes.add(incomeWood);

        QualityUnit upkeepStone = new QualityUnit(ResourecType.STONE, 1);
        List<QualityUnit> upkeep = new ArrayList<>();
//        upkeep.add(upkeepStone);
        return new Enhancement(EnhancementType.LUMBERMILL, MapFieldType.FORREST, incomes, costs, upkeep);
    }

    public static Enhancement getEnhancement(Tool tool) {
        if (tool == null) {
            return null;
        }
        switch (tool) {
            case LUMBERMILL:
                return LUMBERMILL;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "Field Type" + mapFieldType + " gathered Resources";
    }

    public List<QualityUnit> getUpkeep() {
        int factor = 2 ^ level;
        return basicUpkeep.stream().
                map(qu -> new QualityUnit(qu.unit, factor * qu.amount)).
                collect(Collectors.toList());
    }

    public List<QualityUnit> getIncome(double percent) {
        double factor = percent * (2 ^ level);

        return basicIncome.stream().
                map(qu -> new QualityUnit(qu.unit, factor * qu.amount)).
                collect(Collectors.toList());

    }
}
