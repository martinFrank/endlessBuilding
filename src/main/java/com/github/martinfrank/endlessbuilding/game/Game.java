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
import java.util.ArrayList;
import java.util.List;

public class Game implements GuiEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);

    private final List<GameEventListener> gameEventListenerList;
    private Map map;
    private final ResourceManager resourceManager;
    private MapWalker walker;
    private final Thread gameThread;
    private int i = 0;

    private static final int TICK_DURATION_IN_MILLIS = 250;
    private boolean isRunning;

    public Game(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
        gameThread = new Thread(createGameLoop());
        gameEventListenerList = new ArrayList<>();
    }

    private Runnable createGameLoop() {
        return () -> {

            try {
                while (isRunning) {
                    Thread.sleep(TICK_DURATION_IN_MILLIS);
                    tick();
                    i++;
                    if (i % 4 == 0) {
                        LOGGER.debug("tick" + System.currentTimeMillis() / 1000);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }

    public void init() {
        //FIXME errorHandling
        try {
            setupMap();
        } catch (MalformedURLException | JAXBException e) {
            e.printStackTrace();
        }
    }

    private void setupMap() throws MalformedURLException, JAXBException {
        MapPartFactory mapPartFactory = new MapPartFactory();
        MapFactory mapFactory = new MapFactory(mapPartFactory);
        MapLoader maploader = new MapLoader(resourceManager, mapFactory);
        map = maploader.loadDefaultMap();
        walker = mapPartFactory.createWalker();
    }

    private void tick() {
    }

    @Override
    public void mouseSelect(MouseSelection selection) {
        LOGGER.debug("selection {}", selection);
    }

    public Map getMap() {
        return map;
    }

    public void start() {
        isRunning = true;
        gameThread.start();
    }

    public void stop() {
        isRunning = false;
    }

    public void addGameEventListener(GameEventListener gameEventListener) {
        if (!gameEventListenerList.contains(gameEventListener)) {
            gameEventListenerList.add(gameEventListener);
        }
    }
}
