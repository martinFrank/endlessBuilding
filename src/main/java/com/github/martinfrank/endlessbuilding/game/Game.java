package com.github.martinfrank.endlessbuilding.game;

import com.github.martinfrank.endlessbuilding.gui.GuiEventListener;
import com.github.martinfrank.endlessbuilding.gui.MouseSelection;
import com.github.martinfrank.endlessbuilding.map.Map;
import com.github.martinfrank.endlessbuilding.map.MapFactory;
import com.github.martinfrank.endlessbuilding.map.MapPartFactory;
import com.github.martinfrank.endlessbuilding.map.MapWalker;
import com.github.martinfrank.endlessbuilding.res.ResourceManager;
import com.github.martinfrank.maplib.MapStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        startNewGame();
    }

    private void startNewGame() {
        createMap();
    }

    private void createMap() {
        MapPartFactory mapPartFactory = new MapPartFactory();
        MapFactory mapFactory = new MapFactory(mapPartFactory);
        map = mapFactory.createMap(32, 16, MapStyle.HEX_HORIZONTAL);
        map.scale(6f);
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
