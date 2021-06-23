package com.github.martinfrank.endlessbuilding.gui;

import com.github.martinfrank.endlessbuilding.game.Game;
import com.github.martinfrank.endlessbuilding.game.GameEvent;
import com.github.martinfrank.endlessbuilding.game.GameEventListener;
import com.github.martinfrank.endlessbuilding.game.ResourceSummary;
import com.github.martinfrank.endlessbuilding.res.ResourceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ToolboxController implements GameEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ToolboxController.class);

    private final Game game;

    private final ResourceManager resourceManager;

    private final ToolSelection toolSelection;

    private final List<ResourceSummary> data = new ArrayList<>();

    @FXML
    public TableView<ResourceSummary> table;

    @FXML
    public TableColumn<ResourceSummary, String> columnBalance;

    @FXML
    public TableColumn<ResourceSummary, String> columnIncome;

    @FXML
    public TableColumn<ResourceSummary, String> columnResource;

    @FXML
    public ImageView selectedTool;

    @FXML
    public Label basicCosts;

    private ObservableList<ResourceSummary> obsList;

    public ToolboxController(Game game, ResourceManager resourceManager, ToolSelection toolSelection) {
        this.game = game;
        this.resourceManager = resourceManager;
        this.toolSelection = toolSelection;
        game.addGameEventListener(this);
    }

    public void init() {
        obsList = FXCollections.observableArrayList(data);
        columnResource.setCellValueFactory(new PropertyValueFactory<>("resourceType"));
        columnIncome.setCellValueFactory(new PropertyValueFactory<>("income"));
        columnBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
    }


    @Override
    public void gameEvent(GameEvent gameEvent) {
        obsList.clear();
        obsList.addAll(gameEvent.resourceSummarys);
        table.setItems(obsList);
    }

    public void selectFurnaceTool(ActionEvent actionEvent) {
        updateToolIcon(toolSelection.selectFurnaceTool());
    }

    public void selectFarmTool(ActionEvent actionEvent) {
        updateToolIcon(toolSelection.selectFarmTool());
    }

    public void selectMineTool(ActionEvent actionEvent) {
        updateToolIcon(toolSelection.selectMineTool());
    }

    public void selectLumberTool(ActionEvent actionEvent) {
        updateToolIcon(toolSelection.selectLumbermillTool());
    }

    public void selectInfoTool(ActionEvent actionEvent) {
        updateToolIcon(toolSelection.selectInfoTool());
    }

    public void selectHarvestTool(ActionEvent actionEvent) {
        updateToolIcon(toolSelection.selectHarvestTool());
    }

    private void updateToolIcon(Tool tool) {
        LOGGER.debug("Image: {}", resourceManager.image.getIcon(tool));
        selectedTool.setImage(resourceManager.image.getIcon(tool));
    }


}
