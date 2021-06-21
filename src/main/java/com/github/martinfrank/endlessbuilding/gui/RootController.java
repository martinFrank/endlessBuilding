package com.github.martinfrank.endlessbuilding.gui;

import com.github.martinfrank.endlessbuilding.game.Game;
import com.github.martinfrank.endlessbuilding.map.Map;
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
    private CivMapCanvas mapCanvas;

    @FXML
    private TextArea console;


    public RootController(Game game) {
        this.game = game;
        setGuiEventListener(game);
    }


    public void init() {
        LOGGER.debug("mapCanvas: {}", mapCanvas);
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

    public void endTurnButton(ActionEvent actionEvent) {
        game.endTurn();
        redrawMap();
    }
}
