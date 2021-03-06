package com.github.martinfrank.endlessbuilding.mapdata;

import com.github.martinfrank.endlessbuilding.game.Enhancement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapFieldData {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapFieldData.class);

    private MapFieldType mapFieldType;

    private Enhancement enhancement;

    public double getWalkCostFactor() {
        return 1;
    }

    public MapFieldType getMapFieldType() {
        return mapFieldType;
    }

    public void setMapFieldType(MapFieldType mapFieldType) {
        this.mapFieldType = mapFieldType;
    }

    public Enhancement getEnhancement() {
        return enhancement;
    }

    public void setEnhancement(Enhancement enhancement) {
        this.enhancement = enhancement;
    }

    @Override
    public String toString() {
        return "MapFieldData{}";
    }

}
