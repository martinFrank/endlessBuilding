package com.github.martinfrank.endlessbuilding;

import com.github.martinfrank.endlessbuilding.game.Game;
import com.github.martinfrank.endlessbuilding.gui.ControllerFactory;
import com.github.martinfrank.endlessbuilding.gui.RootController;
import com.github.martinfrank.endlessbuilding.gui.ScaleFactor;
import com.github.martinfrank.endlessbuilding.gui.ToolboxController;
import com.github.martinfrank.endlessbuilding.res.ResourceManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class App extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        launch(args);
    }

    private Pane root;

    private Game game;

    @Override
    public void init() {
        ResourceManager resourceManager = new ResourceManager(getClass().getClassLoader());
        game = new Game(resourceManager);
        ControllerFactory controllerFactory = new ControllerFactory(game, resourceManager);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(resourceManager.getGuiRoot());
            fxmlLoader.setControllerFactory(controllerFactory);
            root = fxmlLoader.load();
        } catch (IOException e) {
            LOGGER.debug("error", e);
        }

        game.init();

        RootController rootController = controllerFactory.getRootController();
        rootController.init(resourceManager);

        ToolboxController toolboxController = controllerFactory.getToolboxController();
        toolboxController.init();

        rootController.scaleMap(ScaleFactor.MEDIUM);
    }

    @Override
    public void start(Stage stage) {
        if (root != null) {
            stage.setScene(new Scene(root));
            stage.setTitle("...endless building...");
            stage.show();
        } else {
            LOGGER.error("error during init");
            Platform.exit();
            System.exit(0);
        }

        game.start();
    }

    @Override
    public void stop() throws Exception {
        game.stop();
        super.stop();
    }
}
