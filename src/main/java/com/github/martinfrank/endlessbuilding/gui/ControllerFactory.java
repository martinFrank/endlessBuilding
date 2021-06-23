package com.github.martinfrank.endlessbuilding.gui;

import com.github.martinfrank.endlessbuilding.game.Game;
import javafx.util.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ControllerFactory implements Callback<Class<?>, Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ToolboxController.class);

    private final Game game;

    private RootController rootController;

    public ControllerFactory(Game game) {
        this.game = game;
    }

    @Override
    public Object call(Class<?> type) {
        if (type == RootController.class) {
            LOGGER.debug("create RootController");
            rootController = new RootController(game);
            return rootController;
        }
        if (type == ToolboxController.class) {
            LOGGER.debug("create ToolboxController");
            return new ToolboxController(game);
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


}
