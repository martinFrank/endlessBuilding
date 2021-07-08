package com.github.martinfrank.endlessbuilding.game;

import com.github.martinfrank.endlessbuilding.mapdata.MapFieldType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class Enhancement {

    private static final Logger LOGGER = LoggerFactory.getLogger(Enhancement.class);

//    public static final Enhancement LUMBERMILL = createLumberMill();

    public final EnhancementType type;
    public final List<QualityUnit> basicIncome;
    public final List<MapFieldType> mapFieldTypes;
    public final List<QualityUnit> basicCosts;
    public final List<QualityUnit> basicUpkeep;
    public int level;

    public Enhancement(EnhancementType type, List<MapFieldType> mapFieldTypes, List<QualityUnit> basicIncome, List<QualityUnit> basicCosts, List<QualityUnit> basicUpkeep) {
        this.type = type;
        this.mapFieldTypes = mapFieldTypes;
        this.basicIncome = basicIncome;
        this.basicCosts = basicCosts;
        this.basicUpkeep = basicUpkeep;
        level = 2;
    }

    public static EnhancementType getEnhancementType(Tool tool) {
        if (tool == null) {
            return null;
        }
        switch (tool) {
            case LUMBERMILL:
                return EnhancementType.LUMBERMILL;
            case QUARRY:
                return EnhancementType.QUARRY;
            case MINE:
                return EnhancementType.MINE;
            case FURNACE:
                return EnhancementType.FURNACE;
            case FARM:
                return EnhancementType.FARM;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "Enhancement{" +
                "type=" + type +
                ", basicIncome=" + basicIncome +
                ", mapFieldTypes=" + mapFieldTypes +
                ", basicCosts=" + basicCosts +
                ", basicUpkeep=" + basicUpkeep +
                ", level=" + level +
                '}';
    }

    public List<QualityUnit> getUpkeep() {
        double factor = getLevelFactor();
        return basicUpkeep.stream().
                map(qu -> new QualityUnit(qu.unit, factor * qu.amount)).
                collect(Collectors.toList());
    }

    public List<QualityUnit> getIncome(double percent) {
        double factor = percent * getLevelFactor();
        return basicIncome.stream().
                map(qu -> new QualityUnit(qu.unit, factor * qu.amount)).
                collect(Collectors.toList());

    }

    private double getLevelFactor() {
        return (Math.pow(2, level));
    }
}
