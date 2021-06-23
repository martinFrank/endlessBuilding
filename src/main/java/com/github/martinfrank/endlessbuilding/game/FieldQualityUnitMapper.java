package com.github.martinfrank.endlessbuilding.game;

import com.github.martinfrank.endlessbuilding.mapdata.MapFieldType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FieldQualityUnitMapper {

    public static List<QualityUnit> get(MapFieldType mapFieldType) {
        switch (mapFieldType) {
            case PLAINS:
                return asList(new QualityUnit(ResourecType.HAY, 1));
            case FORREST:
                return asList(new QualityUnit(ResourecType.WOOD, 1));
            case ROCKS:
                return asList(new QualityUnit(ResourecType.STONE, 1));
            case MOUNTAIN:
                return asList(new QualityUnit(ResourecType.STONE, 1.5));
            default:
                return Collections.EMPTY_LIST;
        }
    }


    private static List<QualityUnit> asList(QualityUnit qualityUnit) {
        List<QualityUnit> result = new ArrayList<>();
        result.add(qualityUnit);
        return result;
    }
}
