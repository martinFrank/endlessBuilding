package com.github.martinfrank.endlessbuilding.map;

import com.github.martinfrank.maplib.MapPartFactory;

public class MapFactory extends com.github.martinfrank.maplib.MapFactory<Map, MapField, MapEdge, MapNode, MapWalker> {

    public MapFactory(MapPartFactory<Map, MapField, MapEdge, MapNode, MapWalker> mapPartFactory) {
        super(mapPartFactory);
    }
}
