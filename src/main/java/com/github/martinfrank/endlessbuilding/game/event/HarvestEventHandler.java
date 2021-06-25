package com.github.martinfrank.endlessbuilding.game.event;

import com.github.martinfrank.endlessbuilding.game.*;
import com.github.martinfrank.endlessbuilding.gui.MouseSelection;
import com.github.martinfrank.endlessbuilding.mapdata.MapFieldData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HarvestEventHandler extends MouseEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(HarvestEventHandler.class);

    public HarvestEventHandler(Game game) {
        super(game);
    }

    @Override
    public GameEvent handle(MouseSelection selection) {
        if (selection.isMousePrimary() &&
                selection.hasField() &&
                selection.getTool() == Tool.HARVEST
        ) {
            MapFieldData data = selection.getField().getData();
            if (data.getEnhancement() == null) {
                List<QualityUnit> gatheredResource = FieldQualityUnitMapper.get(selection.getField().getData().getMapFieldType());
                game.addFieldHarvest(gatheredResource);
                return new GameEvent(game);
            }
        }
        return null;
    }
}
