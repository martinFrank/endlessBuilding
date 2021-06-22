package com.github.martinfrank.endlessbuilding.gui;

import com.github.martinfrank.endlessbuilding.game.Game;
import com.github.martinfrank.endlessbuilding.map.Map;
import com.github.martinfrank.endlessbuilding.res.ResourceManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RootController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RootController.class);
    private GuiEventListener eventListener;
    private final Game game;

    @FXML
    private MapCanvas mapCanvas;

    @FXML
    private TextArea console;


    public RootController(Game game) {
        this.game = game;
        setGuiEventListener(game);
    }


    public void init(ResourceManager resourceManager) {
        LOGGER.debug("mapCanvas: {}", mapCanvas);
        mapCanvas.setImageManager(resourceManager);
        mapCanvas.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            int x = (int) mouseEvent.getX();
            int y = (int) mouseEvent.getY();
            MouseSelection selection = mapCanvas.getSelectionAt(x, y);
            eventListener.mouseSelect(selection);
        });
        setMap(game.getMap());
    }

    public void setMap(Map map) {
        mapCanvas.setMap(map);
        redrawMap();
    }

    public void setGuiEventListener(GuiEventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void redrawMap() {
        mapCanvas.drawMap();
    }

    public void clearConsole() {
        console.clear();
    }

    public void scaleMap(ScaleFactor factor) {
        mapCanvas.setScaleFactor(factor);

    }

    public void setScaleSmall(ActionEvent actionEvent) {
        scaleMap(ScaleFactor.SMALL);
    }

    public void setScaleMedium(ActionEvent actionEvent) {
        scaleMap(ScaleFactor.MEDIUM);
    }

    public void setScaleBig(ActionEvent actionEvent) {
        scaleMap(ScaleFactor.BIG);
    }

    public void pauseGame(ActionEvent actionEvent) {

    }

    public void continueGame(ActionEvent actionEvent) {
    }
}
