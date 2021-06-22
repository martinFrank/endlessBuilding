package com.github.martinfrank.endlessbuilding.game;

import com.github.martinfrank.endlessbuilding.game.map.MapLoader;
import com.github.martinfrank.endlessbuilding.gui.GuiEventListener;
import com.github.martinfrank.endlessbuilding.gui.MouseSelection;
import com.github.martinfrank.endlessbuilding.map.Map;
import com.github.martinfrank.endlessbuilding.map.MapFactory;
import com.github.martinfrank.endlessbuilding.map.MapPartFactory;
import com.github.martinfrank.endlessbuilding.map.MapWalker;
import com.github.martinfrank.endlessbuilding.res.ResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.net.MalformedURLException;

public class Game implements GuiEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);

    private Map map;
    private final ResourceManager resourceManager;
    private MapWalker walker;

    public Game(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    public void endTurn() {
    }

    public void init() {
        try {
            setupMap();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void setupMap() throws MalformedURLException, JAXBException {
        MapPartFactory mapPartFactory = new MapPartFactory();
        MapFactory mapFactory = new MapFactory(mapPartFactory);
        MapLoader maploader = new MapLoader(resourceManager, mapFactory);
        map = maploader.loadDefaultMap();
//        map.scale(7.5f);
        //big: scale=30, width=120,h=120 -> Image(getImageUrl(mapFieldType).toString(),120,120,false,true);
        //medium: scale=15, width=60,h=60 -> Image(getImageUrl(mapFieldType).toString(),60,60,false,true);
        //small: scale=7.5, width=30,h=30 -> Image(getImageUrl(mapFieldType).toString(),31,31,false,true);
        walker = mapPartFactory.createWalker();
    }

    @Override
    public void mouseSelect(MouseSelection selection) {
        LOGGER.debug("selection {}", selection);
    }

    public Map getMap() {
        return map;
    }

}
