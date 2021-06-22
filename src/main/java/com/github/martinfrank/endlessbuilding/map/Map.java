package com.github.martinfrank.endlessbuilding.map;

import com.github.martinfrank.endlessbuilding.mapdata.MapData;
import com.github.martinfrank.maplib.MapStyle;

public class Map extends com.github.martinfrank.maplib.Map<MapData, MapField, MapEdge, MapNode, MapWalker> {

    public Map(int width, int height, MapStyle style, MapData mapData) {
        super(width, height, style, mapData);
    }

}
