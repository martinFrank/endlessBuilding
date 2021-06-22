package com.github.martinfrank.endlessbuilding.mapdata;

public class MapFieldData {

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
