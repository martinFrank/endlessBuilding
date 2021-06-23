package com.github.martinfrank.endlessbuilding.gui;

import com.github.martinfrank.endlessbuilding.game.Game;
import com.github.martinfrank.endlessbuilding.game.GameEvent;
import com.github.martinfrank.endlessbuilding.game.GameEventListener;
import com.github.martinfrank.endlessbuilding.map.Map;
import com.github.martinfrank.endlessbuilding.res.ResourceManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RootController implements GameEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(RootController.class);

    private GuiEventListener eventListener;
    private final Game game;

    @FXML
    public ToggleGroup radioButtonGroup;

    @FXML
    private MapCanvas mapCanvas;

    public RootController(Game game) {
        this.game = game;
        setGuiEventListener(game);
    }


    public void init(ResourceManager resourceManager) {
        mapCanvas.setImageManager(resourceManager);
        mapCanvas.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            int x = (int) mouseEvent.getX();
            int y = (int) mouseEvent.getY();
            MouseSelection selection = mapCanvas.getSelectionAt(x, y);
            eventListener.mouseSelect(selection);
        });

        game.addGameEventListener(this);

        setMap(game.getMap());
        initIncomeTable();
    }

    private void initIncomeTable() {

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

    public void togglePause(ActionEvent actionEvent) {

    }

    public void continueGame(ActionEvent actionEvent) {
    }

    public void selectLumber(ActionEvent actionEvent) {
    }

    @Override
    public void gameEvent(GameEvent gameEvent) {

    }
}
