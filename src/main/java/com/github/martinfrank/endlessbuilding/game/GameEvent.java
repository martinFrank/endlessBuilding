package com.github.martinfrank.endlessbuilding.game;

import java.util.ArrayList;
import java.util.List;

public class GameEvent {

    public final List<ResourceSummary> resourceSummarys;

    public GameEvent(Game game) {
        resourceSummarys = new ArrayList<>();
        for (ResourecType resourecType : ResourecType.values()) {
            ResourceSummary resourceSummary = new ResourceSummary(resourecType);
            double balance = game.balance.get(resourecType) == null ? 0 : game.balance.get(resourecType);
            resourceSummary.setBalance(balance);
            resourceSummary.setIncome(0);
            resourceSummarys.add(resourceSummary);
        }
    }
}
