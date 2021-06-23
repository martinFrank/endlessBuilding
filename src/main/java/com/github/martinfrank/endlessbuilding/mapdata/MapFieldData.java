package com.github.martinfrank.endlessbuilding.mapdata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapFieldData {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapFieldData.class);

    private MapFieldType mapFieldType;

    public double getWalkCostFactor() {
        return 1;
    }

    public MapFieldType getMapFieldType() {
        return mapFieldType;
    }

    public void setMapFieldType(MapFieldType mapFieldType) {
        this.mapFieldType = mapFieldType;
    }

    @Override
    public String toString() {
        return "MapFieldData{}";
    }

}
