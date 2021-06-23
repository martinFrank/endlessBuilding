package com.github.martinfrank.endlessbuilding.gui;

import com.github.martinfrank.endlessbuilding.game.Game;
import com.github.martinfrank.endlessbuilding.res.ResourceManager;
import javafx.util.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ControllerFactory implements Callback<Class<?>, Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ToolboxController.class);

    private final Game game;

    private final ResourceManager resourceManager;

    private final ToolSelection toolSelection;

    private RootController rootController;

    private ToolboxController toolboxController;

    public ControllerFactory(Game game, ResourceManager resourceManager) {
        this.game = game;
        this.resourceManager = resourceManager;
        toolSelection = new ToolSelection();
    }

    @Override
    public Object call(Class<?> type) {
        if (type == RootController.class) {
            LOGGER.debug("create RootController");
            rootController = new RootController(game, toolSelection);
            return rootController;
        }
        if (type == ToolboxController.class) {
            LOGGER.debug("create ToolboxController");
            toolboxController = new ToolboxController(game, resourceManager, toolSelection);
            return toolboxController;
        } else {
            // default behavior for controllerFactory:
            try {
                return type.getDeclaredConstructor().newInstance();
            } catch (Exception exc) {
                exc.printStackTrace();
                throw new RuntimeException(exc); // fatal, just bail...
            }
        }
    }

    public RootController getRootController() {
        return rootController;
    }

    public ToolboxController getToolboxController() {
        return toolboxController;
    }

}
