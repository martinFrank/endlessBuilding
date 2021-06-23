package com.github.martinfrank.endlessbuilding.res;

import com.github.martinfrank.endlessbuilding.gui.ScaleFactor;
import com.github.martinfrank.endlessbuilding.gui.Tool;
import com.github.martinfrank.endlessbuilding.mapdata.MapFieldType;
import javafx.scene.image.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ResourceManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceManager.class);

    private final ClassLoader classLoader;
    public final ImageManager image;

    public ResourceManager(ClassLoader classLoader) {
        this.classLoader = classLoader;
        image = new ImageManager();
    }


    public URL getDefaultMap() throws MalformedURLException {
        return resourceURL("map/map.xml");
    }

    public URL getGuiRoot() throws MalformedURLException {
        return resourceURL("gui/root.fxml");
    }

    private URL resourceURL(String str) throws MalformedURLException {
        URL url = classLoader.getResource(str);
        if (url != null) {
            return new File(url.getPath()).toURI().toURL();
        }
        throw new MalformedURLException("url=null");
    }

    public URL getMapFieldImage(String filename) throws MalformedURLException {
        return resourceURL("PNG/Tiles/" + filename);
    }


    public class ImageManager {

        private final Map<ScaleFactor, Map<MapFieldType, Image>> images = new HashMap<>();
        private final Map<Tool, Image> icons = new HashMap<>();

        private ImageManager() {
            images.put(ScaleFactor.BIG, new HashMap<>());
            images.put(ScaleFactor.MEDIUM, new HashMap<>());
            images.put(ScaleFactor.SMALL, new HashMap<>());
        }

        public Image getMapTileImage(MapFieldType mapFieldType, ScaleFactor scaleFactor) {
            if (mapFieldType == MapFieldType.WATER) {
                return null;
            }
            Image fromMap = images.get(scaleFactor).get(mapFieldType);
            if (fromMap == null) {
                try {
                    fromMap = loadImage(mapFieldType, scaleFactor);
                    images.get(scaleFactor).put(mapFieldType, fromMap);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

            }
            return fromMap;
        }

        private Image loadImage(MapFieldType mapFieldType, ScaleFactor scaleFactor) throws MalformedURLException {
            return new Image(getImageUrl(mapFieldType).toString(), scaleFactor.tileWidth, scaleFactor.tileHeight, false, true);
        }

        private URL getImageUrl(MapFieldType mapFieldType) throws MalformedURLException {
            return ResourceManager.this.getMapFieldImage(imageMapping(mapFieldType));
        }

        private String imageMapping(MapFieldType mapFieldType) {
            switch (mapFieldType) {
                case PLAINS:
                    return "Terrain/Grass/grass_05.png";
                case FORREST:
                    return "Terrain/Grass/grass_12.png";
                case ROCKS:
                    return "Terrain/Grass/grass_15.png";
                case MOUNTAIN:
                    return "Terrain/Grass/grass_14.png";
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
            return ResourceManager.this.getMapFieldImage(iconMapping(tool));
        }

        private String iconMapping(Tool tool) {
            switch (tool) {
                case INFO:
                    return "infoTile.png";
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
}
