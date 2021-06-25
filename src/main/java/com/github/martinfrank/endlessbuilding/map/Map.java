package com.github.martinfrank.endlessbuilding.map;

import com.github.martinfrank.endlessbuilding.game.Enhancement;
import com.github.martinfrank.endlessbuilding.mapdata.MapData;
import com.github.martinfrank.maplib.MapStyle;

import java.util.List;
import java.util.stream.Collectors;

public class Map extends com.github.martinfrank.maplib.Map<MapData, MapField, MapEdge, MapNode, MapWalker> {

    public Map(int width, int height, MapStyle style, MapData mapData) {
        super(width, height, style, mapData);
    }

    public List<Enhancement> getEnhancements() {
        return getFields().stream().
                filter(f -> f.getData().getEnhancement() != null).
                map(f -> f.getData().getEnhancement()).
                collect(Collectors.toList());
    }
}
