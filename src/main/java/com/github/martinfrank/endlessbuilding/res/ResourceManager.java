package com.github.martinfrank.endlessbuilding.res;

import com.github.martinfrank.endlessbuilding.gui.ScaleFactor;
import com.github.martinfrank.endlessbuilding.mapdata.MapFieldType;
import javafx.scene.image.Image;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ResourceManager {

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
        String targetImage = "PNG/Tiles/" + filename;
        System.out.println("targetImage: " + targetImage);
        return resourceURL("PNG/Tiles/" + filename);
    }


    public class ImageManager {

        private final Map<ScaleFactor, Map<MapFieldType, Image>> images = new HashMap<>();

        private ImageManager() {
            images.put(ScaleFactor.BIG, new HashMap<>());
            images.put(ScaleFactor.MEDIUM, new HashMap<>());
            images.put(ScaleFactor.SMALL, new HashMap<>());
        }

        public Image getImage(MapFieldType mapFieldType, ScaleFactor scaleFactor) {
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
                    return "Terrain/Grass/grass_03.png";
                default:
                    return "Terrain/Grass/grass_05.png";
            }
        }
    }
}
