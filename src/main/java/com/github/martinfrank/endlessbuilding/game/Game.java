package com.github.martinfrank.endlessbuilding.game;

import com.github.martinfrank.endlessbuilding.game.event.AddEnhancementHandler;
import com.github.martinfrank.endlessbuilding.game.event.HarvestEventHandler;
import com.github.martinfrank.endlessbuilding.game.event.MouseEventHandler;
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
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
        mouseEventHandlers.add(new AddEnhancementHandler(this));
        balance = new HashMap<>();
        initBalance();
    }

    private void initBalance() {
        for (ResourecType resourecType : ResourecType.values()) {
            balance.put(resourecType, 0d);
        }
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
        gatherRessources();
        notifyGui(new GameEvent(this));
    }

    private void gatherRessources() {
        List<Enhancement> enhancements = map.getEnhancements();
        enhancements.forEach(this::gatherRessources);
    }

    private void gatherRessources(Enhancement enhancement) {
        List<QualityUnit> upkeepCost = enhancement.getUpkeep();
        List<QualityUnit> withdrawFromBalance = reduceBalance(upkeepCost);
        double percent = calculatePercent(upkeepCost, withdrawFromBalance);
        LOGGER.debug("factor (%) " + percent);
        gatherRessources(enhancement, percent);
    }

    private void gatherRessources(Enhancement enhancement, double percent) {
        List<QualityUnit> outcomes = enhancement.getIncome(percent);
        for (QualityUnit outcome : outcomes) {
            double current = balance.get(outcome.unit);
            double temp = current;
            current = current + outcome.amount;
            balance.put(outcome.unit, current);
            LOGGER.debug("increasing {} from {} to {} (walue:{}", outcome.unit, temp, current, outcome.amount);
        }
    }

    private double calculatePercent(List<QualityUnit> upkeepCost, List<QualityUnit> fromBalance) {
        double percent = 1;
        for (QualityUnit demand : upkeepCost) {
            Optional<QualityUnit> balOpt = fromBalance.stream().filter(qu -> qu.unit == demand.unit).findAny();
            if (!balOpt.isPresent()) {
                return 0;
            } else {
                double bal = balOpt.get().amount;
                double dem = demand.amount;
                double newPercent = bal / dem;
                if (newPercent < percent) {
                    percent = newPercent;
                }
            }
        }
        return percent;
    }

    public List<QualityUnit> reduceBalance(List<QualityUnit> expenses) {
        List<QualityUnit> outputFromBalance = new ArrayList<>();
        for (QualityUnit demand : expenses) {
            double ret;
            double bal = balance.get(demand.unit);
            if (demand.amount > bal) {
                ret = bal;
                balance.put(demand.unit, 0d);
            } else {
                ret = demand.amount;
                balance.put(demand.unit, bal - demand.amount);
            }
            outputFromBalance.add(new QualityUnit(demand.unit, ret));
        }
        return outputFromBalance;
    }

    public boolean hasBalance(List<QualityUnit> expenses) {
        for (QualityUnit demand : expenses) {
            double bal = balance.get(demand.unit);
            LOGGER.debug("demand: " + demand.amount + " of " + demand.unit + "  in stock: " + bal);
            if (demand.amount > bal) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void mouseSelect(MouseSelection selection) {
        //FIXME Feedback from handler
        mouseEventHandlers.forEach(meh -> notifyGui(meh.handle(selection)));
    }

    public void notifyGui(GameEvent gameEvent) {
        LOGGER.debug("gui event? :" + gameEvent);
        if (gameEvent != null) {
            for (GameEventListener eventListener : gameEventListeners) {
                eventListener.gameEvent(gameEvent);
            }
        }
    }

    public void addFieldHarvest(List<QualityUnit> gatheredResource) {
        for (QualityUnit qu : gatheredResource) {
            double current = balance.get(qu.unit) == null ? 0 : balance.get(qu.unit);
            current = current + qu.amount;
            balance.put(qu.unit, current);
        }
    }

    public Map getMap() {
        return map;
    }

    public void start() {
        isRunning = true;
        gameThread.start();
        notifyGui(new GameEvent(this));
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
