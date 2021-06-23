package com.github.martinfrank.endlessbuilding.game;

import com.github.martinfrank.endlessbuilding.game.event.HarvestEventHandler;
import com.github.martinfrank.endlessbuilding.game.event.MouseEventHandler;
import com.github.martinfrank.endlessbuilding.game.map.MapLoader;
import com.github.martinfrank.endlessbuilding.gui.GuiEventListener;
import com.github.martinfrank.endlessbuilding.gui.MouseSelection;
import com.github.martinfrank.endlessbuilding.map.Map;
import com.github.martinfrank.endlessbuilding.map.MapFactory;
import com.github.martinfrank.endlessbuilding.map.MapPartFactory;
import com.github.martinfrank.endlessbuilding.map.MapWalker;
import com.github.martinfrank.endlessbuilding.mapdata.MapFieldType;
import com.github.martinfrank.endlessbuilding.res.ResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game implements GuiEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);

    private final List<GameEventListener> gameEventListeners;
    private Map map;
    private final ResourceManager resourceManager;
    private MapWalker walker;
    private final Thread gameThread;
    private int i = 0;

    private final List<MouseEventHandler> mouseEventHandlers;

    public final java.util.Map<ResourecType, Double> balance;

    private static final int TICK_DURATION_IN_MILLIS = 250;
    private boolean isRunning;

    public Game(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
        gameThread = new Thread(createGameLoop());
        gameEventListeners = new ArrayList<>();
        mouseEventHandlers = new ArrayList<>();
        mouseEventHandlers.add(new HarvestEventHandler(this));
        balance = new HashMap<>();
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
        mouseEventHandlers.forEach(meh -> meh.handle(selection));
    }

    public void addFieldHarvest(List<QualityUnit> gatheredResource) {
        for (QualityUnit qu : gatheredResource) {
            double current = balance.get(qu.unit) == null ? 0 : balance.get(qu.unit);
            current = current + qu.amount;
            balance.put(qu.unit, current);
        }
        updateGameEventListener();

    }

    private void updateGameEventListener() {

        GameEvent gameEvent = new GameEvent(this);
        for (GameEventListener eventListener : gameEventListeners) {
            eventListener.gameEvent(gameEvent);
        }
    }

    private void gatherRessource(MapFieldType mapFieldType) {

    }


    public Map getMap() {
        return map;
    }

    public void start() {
        isRunning = true;
        gameThread.start();
        updateGameEventListener();
    }

    public void stop() {
        isRunning = false;
    }

    public void addGameEventListener(GameEventListener gameEventListener) {
        if (!gameEventListeners.contains(gameEventListener)) {
            gameEventListeners.add(gameEventListener);
        }
    }
}
