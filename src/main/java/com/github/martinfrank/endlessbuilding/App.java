package com.github.martinfrank.endlessbuilding;

import com.github.martinfrank.endlessbuilding.game.Game;
import com.github.martinfrank.endlessbuilding.gui.ControllerFactory;
import com.github.martinfrank.endlessbuilding.gui.RootController;
import com.github.martinfrank.endlessbuilding.gui.ScaleFactor;
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

    @Override
    public void init() {
        ResourceManager resourceManager = new ResourceManager(getClass().getClassLoader());
        Game game = new Game(resourceManager);
        ControllerFactory controllerFactory = new ControllerFactory(game);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(resourceManager.getGuiRoot());
            fxmlLoader.setControllerFactory(controllerFactory);
            root = fxmlLoader.load();
        } catch (IOException e) {
            LOGGER.debug("error", e);
        }
        RootController rootController = controllerFactory.getRootController();
        game.init();
        rootController.init(resourceManager);
        rootController.scaleMap(ScaleFactor.SMALL);
    }

    @Override
    public void start(Stage stage) {
        if (root != null) {
            stage.setScene(new Scene(root));
            stage.setTitle("tbd: set title");
            stage.show();
        } else {
            LOGGER.debug("error during init");
            Platform.exit();
            System.exit(0);
        }
    }

}
