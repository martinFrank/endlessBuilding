package com.github.martinfrank.endlessbuilding.map;

import com.github.martinfrank.endlessbuilding.mapdata.MapData;
import com.github.martinfrank.endlessbuilding.mapdata.MapEdgeData;
import com.github.martinfrank.endlessbuilding.mapdata.MapFieldData;
import com.github.martinfrank.endlessbuilding.mapdata.MapNodeData;
import com.github.martinfrank.maplib.MapStyle;

public class MapPartFactory extends com.github.martinfrank.maplib.MapPartFactory<Map, MapField, MapEdge, MapNode, MapWalker> {

    @Override
    public MapNode createMapNode() {
        return new MapNode(new MapNodeData());
    }

    @Override
    public MapEdge createMapEdge() {
        return new MapEdge(new MapEdgeData());
    }

    @Override
    public MapField createMapField() {
        return new MapField(new MapFieldData());
    }

    @Override
    public Map createMap(int columns, int rows, MapStyle style) {
        return new Map(columns, rows, style, new MapData());
    }

    @Override
    public MapWalker createWalker() {
        return new MapWalker();
    }
}
