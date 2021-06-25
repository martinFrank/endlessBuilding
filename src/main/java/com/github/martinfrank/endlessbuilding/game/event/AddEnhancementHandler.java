package com.github.martinfrank.endlessbuilding.game.event;

import com.github.martinfrank.endlessbuilding.game.*;
import com.github.martinfrank.endlessbuilding.gui.MouseSelection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AddEnhancementHandler extends MouseEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddEnhancementHandler.class);

    public AddEnhancementHandler(Game game) {
        super(game);
    }

    @Override
    public GameEvent handle(MouseSelection selection) {
        if (selection.isMousePrimary() &&
                selection.hasField() &&
                selection.getField().getData().getEnhancement() == null) {
            LOGGER.debug("handle {}", selection.getTool());
            if (selection.getTool() == Tool.LUMBERMILL ||
                    selection.getTool() == Tool.FARM ||
                    selection.getTool() == Tool.FURNACE ||
                    selection.getTool() == Tool.MINE) {

                Enhancement enhancement = Enhancement.getEnhancement(selection.getTool());

                LOGGER.debug("enhancement to set: " + enhancement);

                if (enhancement != null && selection.getField().getData().getMapFieldType() == enhancement.mapFieldType) {
                    List<QualityUnit> price = enhancement.basicCosts;
                    LOGGER.debug("enhancement to set: " + enhancement);
                    if (game.hasBalance(price)) {
                        LOGGER.debug("has balance!");
                        game.reduceBalance(price);
                        selection.getField().getData().setEnhancement(enhancement);
                        //FIXME new event
                        return new GameEvent(game);
                    } else {
                        LOGGER.debug("has NOT balance!");
                        //FIXME proper warning!
//                        return new GameEvent(game);
                    }


                }
            }

        }
        return null;
    }
}
