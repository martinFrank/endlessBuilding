package com.github.martinfrank.endlessbuilding.game.event;

import com.github.martinfrank.endlessbuilding.game.*;
import com.github.martinfrank.endlessbuilding.gui.MouseSelection;
import com.github.martinfrank.endlessbuilding.res.ResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.net.MalformedURLException;
import java.util.List;

public class AddEnhancementHandler extends MouseEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddEnhancementHandler.class);

    //FIXME enhancementLoader into ResourceManager!!!
    private final ResourceManager resourceManager;

    public AddEnhancementHandler(Game game, ResourceManager resourceManager) throws MalformedURLException, JAXBException {
        super(game);
        this.resourceManager = resourceManager;
    }

    @Override
    public GameEvent handle(MouseSelection selection) {
        if (selection.isMousePrimary() &&
                selection.hasField() &&
                selection.getField().getData().getEnhancement() == null) {
            if (selection.getTool() == Tool.LUMBERMILL ||
                    selection.getTool() == Tool.FARM ||
                    selection.getTool() == Tool.FURNACE ||
                    selection.getTool() == Tool.MINE) {

                EnhancementType enhancementType = Enhancement.getEnhancementType(selection.getTool());
                Enhancement enhancement = resourceManager.enhancementManager.getEnhancement(enhancementType);

                LOGGER.debug("enhancement to set: " + enhancement);

                if (enhancement != null && enhancement.mapFieldTypes.contains(selection.getField().getData().getMapFieldType())) {
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
