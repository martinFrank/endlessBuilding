package com.github.martinfrank.endlessbuilding.res;

import com.github.martinfrank.endlessbuilding.game.EnhancementType;
import com.github.martinfrank.endlessbuilding.game.Tool;
import com.github.martinfrank.endlessbuilding.gui.ScaleFactor;
import com.github.martinfrank.endlessbuilding.mapdata.MapFieldType;
import javafx.scene.image.Image;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ImageManager {

    private final Map<ScaleFactor, Map<MapFieldType, Image>> mapTiles = new HashMap<>();
    private final Map<ScaleFactor, Map<EnhancementType, Image>> enhancementTiles = new HashMap<>();
    private final Map<Tool, Image> icons = new HashMap<>();

    private final ResourceManager resourceManager;

    public ImageManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;

        mapTiles.put(ScaleFactor.BIG, new HashMap<>());
        mapTiles.put(ScaleFactor.MEDIUM, new HashMap<>());
        mapTiles.put(ScaleFactor.SMALL, new HashMap<>());

        enhancementTiles.put(ScaleFactor.BIG, new HashMap<>());
        enhancementTiles.put(ScaleFactor.MEDIUM, new HashMap<>());
        enhancementTiles.put(ScaleFactor.SMALL, new HashMap<>());
    }

    public Image getMapTileImage(MapFieldType mapFieldType, ScaleFactor scaleFactor) {
        if (mapFieldType == MapFieldType.WATER) {
            return null;
        }
        Image fromMap = mapTiles.get(scaleFactor).get(mapFieldType);
        if (fromMap == null) {
            try {
                fromMap = loadImage(mapFieldType, scaleFactor);
                mapTiles.get(scaleFactor).put(mapFieldType, fromMap);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        }
        return fromMap;
    }

    public Image getEnhancementTileImage(EnhancementType enhancementType, ScaleFactor scaleFactor) {
        Image fromMap = enhancementTiles.get(scaleFactor).get(enhancementType);
        if (fromMap == null) {
            try {
                fromMap = loadImage(enhancementType, scaleFactor);
                enhancementTiles.get(scaleFactor).put(enhancementType, fromMap);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        }
        return fromMap;
    }

    private Image loadImage(MapFieldType mapFieldType, ScaleFactor scaleFactor) throws MalformedURLException {
        return new Image(getImageUrl(mapFieldType).toString(), scaleFactor.tileWidth, scaleFactor.tileHeight, false, true);
    }

    private Image loadImage(EnhancementType enhancementType, ScaleFactor scaleFactor) throws MalformedURLException {
        return new Image(getImageUrl(enhancementType).toString(), scaleFactor.tileWidth, scaleFactor.tileHeight, false, true);
    }

    private URL getImageUrl(MapFieldType mapFieldType) throws MalformedURLException {
        return resourceManager.getMapFieldImage(imageMapping(mapFieldType));
    }

    private URL getImageUrl(EnhancementType enhancementType) throws MalformedURLException {
        return resourceManager.getMapFieldImage(imageMapping(enhancementType));
    }

    private String imageMapping(MapFieldType mapFieldType) {
        switch (mapFieldType) {
            case PLAIN:
                return "Terrain/Grass/grass_05.png";
            case FORREST:
                return "Terrain/Grass/grass_12.png";
            case ROCK:
                return "Terrain/Grass/grass_15.png";
            case MOUNTAIN:
                return "Terrain/Grass/grass_14.png";
            default:
                return "Terrain/Grass/grass_03.png";
        }
    }

    private String imageMapping(EnhancementType enhancementType) {
        switch (enhancementType) {
            case LUMBERMILL:
                return "Medieval/medieval_lumber.png";
            default:
                return "Terrain/Grass/grass_03.png";
        }
    }

    public Image getIcon(Tool tool) {
        Image fromBuffer = icons.get(tool);
        if (fromBuffer == null) {
            try {
                fromBuffer = loadIcon(tool);
                icons.put(tool, fromBuffer);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return fromBuffer;
    }

    private Image loadIcon(Tool tool) throws MalformedURLException {
        return new Image(getImageUrl(tool).toString());
    }

    private URL getImageUrl(Tool tool) throws MalformedURLException {
        return resourceManager.getMapFieldImage(iconMapping(tool));
    }

    private String iconMapping(Tool tool) {
        switch (tool) {
            case HARVEST:
                return "harvest.png";
            case LUMBERMILL:
                return "Medieval/medieval_lumber.png";
            case MINE:
                return "Medieval/medieval_mine_2.png";
            case FARM:
                return "Medieval/medieval_farm.png";
            case FURNACE:
                return "Medieval/medieval_blacksmith.png";
//                case FORREST:
//                    return "Terrain/Grass/grass_12.png";
//                case ROCKS:
//                    return "Terrain/Grass/grass_15.png";
//                case MOUNTAIN:
//                    return "Terrain/Grass/grass_14.png";
            default:
                return "infoTile.png";
        }
    }

}
