package com.github.martinfrank.endlessbuilding.gui;

import com.github.martinfrank.endlessbuilding.game.Game;
import com.github.martinfrank.endlessbuilding.game.GameEvent;
import com.github.martinfrank.endlessbuilding.game.GameEventListener;
import com.github.martinfrank.endlessbuilding.map.Map;
import com.github.martinfrank.endlessbuilding.map.MapField;
import com.github.martinfrank.endlessbuilding.res.ResourceManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RootController implements GameEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(RootController.class);

    private final Game game;
    private final ToolSelection toolSelection;
    private MapField currentFieldInfo;

    @FXML
    public ToggleGroup radioButtonGroup;
    public TextArea infoTextArea;

    @FXML
    private MapCanvas mapCanvas;

    public RootController(Game game, ToolSelection toolSelection) {
        this.game = game;
        this.toolSelection = toolSelection;
    }


    public void init(ResourceManager resourceManager) {
        mapCanvas.setImageManager(resourceManager);
        mapCanvas.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            int x = (int) mouseEvent.getX();
            int y = (int) mouseEvent.getY();
            MouseSelection selection = mapCanvas.getSelectionAt(x, y);
            selection.setMousePrimary(mouseEvent.getButton() == MouseButton.PRIMARY);
            selection.setMouseSecondary(mouseEvent.getButton() == MouseButton.SECONDARY);
            selection.setTool(toolSelection.selected);
            game.mouseSelect(selection);
        });
        mapCanvas.addEventFilter(MouseEvent.MOUSE_MOVED, mouseEvent -> {
            int x = (int) mouseEvent.getX();
            int y = (int) mouseEvent.getY();
            MouseSelection selection = mapCanvas.getSelectionAt(x, y);
            if (selection.hasField()) {
                showInfo(selection.getField());
            }
        });

        game.addGameEventListener(this);
        setMap(game.getMap());
    }

    private void showInfo(MapField field) {
        if (currentFieldInfo != field) { //yes: it is == not .equals()!
            currentFieldInfo = field;
            String infoText = MapFieldInfoGenerator.generateInfo(currentFieldInfo);
            infoTextArea.setText(infoText);
        }
    }


    public void setMap(Map map) {
        mapCanvas.setMap(map);
        redrawMap();
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

    @Override
    public void gameEvent(GameEvent gameEvent) {
        //FIXME - GameEvent should provide dedicated info
        mapCanvas.drawMap();
    }
}
