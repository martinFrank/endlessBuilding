package com.github.martinfrank.endlessbuilding.map;

import com.github.martinfrank.endlessbuilding.mapdata.MapData;
import com.github.martinfrank.maplib.MapStyle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Map extends com.github.martinfrank.maplib.Map<MapData, MapField, MapEdge, MapNode, MapWalker> {


    public Map(int width, int height, MapStyle style, MapData mapData) {
        super(width, height, style, mapData);
    }

    public MapField getRandomField(Random random) {
        int size = getFields().size();
        return getFields().get(random.nextInt(size));
    }

    public List<MapField> getFields(MapField center, int radius) {
        List<MapField> inside = new ArrayList<>(Collections.singletonList(center));
        List<MapField> fields = new ArrayList<>();
        for (int i = 0; i < radius; i++) {
            fields.clear();
            for (MapField in : inside) {
                for (MapField nbg : in.getFields()) {
                    if (!inside.contains(nbg) && !fields.contains(nbg)) {
                        fields.add(nbg);
                    }
                }
            }
            inside.addAll(fields);
        }
        return fields;
    }

}
