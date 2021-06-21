package com.github.martinfrank.endlessbuilding.map;

import java.util.List;

public class MapWalker extends com.github.martinfrank.maplib.MapWalker<MapField, MapEdge, MapNode> {

    @Override
    public boolean canEnter(MapField from, MapField into) {
        return true;
    }

    @Override
    public int getEnterCosts(MapField from, MapField into) {
        return (int) into.getData().getWalkCostFactor() * 10;
    }

    @Override
    public List<MapField> getNeighbours(MapField field) {
        return getNeighboursFromEdges(field);
    }

}
