package com.github.martinfrank.endlessbuilding.map;

import com.github.martinfrank.endlessbuilding.mapdata.MapFieldData;

public class MapField extends com.github.martinfrank.maplib.MapField<MapFieldData, MapField, MapEdge, MapNode> {

    public MapField(MapFieldData mapFieldData) {
        super(mapFieldData);
    }

    @Override
    public String toString() {
        return getData().toString();
    }

}
