package com.github.martinfrank.endlessbuilding.gui;

import com.github.martinfrank.endlessbuilding.game.*;
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
    public Label toolName;

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

    private void updateToolSelection(Tool tool) {
        updateToolIcon(tool);
        toolName.setText(tool.name());
        Enhancement enhancement = resourceManager.enhancementManager.getEnhancement(Enhancement.getEnhancementType(tool));
        LOGGER.debug("enhancement {}", enhancement);
        if (enhancement != null) {
            basicCosts.setText(enhancement.basicCosts.toString());
        }
    }


    public void selectFurnaceTool(ActionEvent actionEvent) {
        updateToolSelection(toolSelection.selectFurnaceTool());
    }

    public void selectFarmTool(ActionEvent actionEvent) {
        updateToolSelection(toolSelection.selectFarmTool());
    }

    public void selectMineTool(ActionEvent actionEvent) {
        updateToolSelection(toolSelection.selectMineTool());
    }

    public void selectLumberTool(ActionEvent actionEvent) {
        updateToolSelection(toolSelection.selectLumbermillTool());
    }

    public void selectHarvestTool(ActionEvent actionEvent) {
        updateToolSelection(toolSelection.selectHarvestTool());
    }

    private void updateToolIcon(Tool tool) {
        LOGGER.debug("Image: {}", resourceManager.image.getIcon(tool));
        selectedTool.setImage(resourceManager.image.getIcon(tool));
    }


}
