package com.github.martinfrank.endlessbuilding.gui;

import com.github.martinfrank.endlessbuilding.game.Game;
import com.github.martinfrank.endlessbuilding.game.GameEvent;
import com.github.martinfrank.endlessbuilding.game.GameEventListener;
import com.github.martinfrank.endlessbuilding.game.ResourceSummary;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ToolboxController implements GameEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ToolboxController.class);

    private final Game game;

    private final List<ResourceSummary> data = new ArrayList<>();

    @FXML
    public TableView<ResourceSummary> table;

    @FXML
    public TableColumn<ResourceSummary, String> columnBalance;

    @FXML
    public TableColumn<ResourceSummary, String> columnIncome;

    @FXML
    public TableColumn<ResourceSummary, String> columnResource;


    private ObservableList<ResourceSummary> obsList;
    private boolean once = false;

    public ToolboxController(Game game) {
        this.game = game;
        game.addGameEventListener(this);
    }

    private void initTable() {

        obsList = FXCollections.observableArrayList(data);
//        table.getItems()
        //clmTicketName.setCellValueFactory(new PropertyValueFactory<Ticket, String>("ticketName"));
        columnResource.setCellValueFactory(new PropertyValueFactory<ResourceSummary, String>("resourceType"));
        columnIncome.setCellValueFactory(new PropertyValueFactory<ResourceSummary, String>("income"));
        columnBalance.setCellValueFactory(new PropertyValueFactory<ResourceSummary, String>("balance"));
    }

    public void selectLumber(ActionEvent actionEvent) {
        if (!once) {
            once = true;
            initTable();
        }
        LOGGER.debug("peng peng {}", table);
//        data.add("peng");

        ResourceSummary rs = new ResourceSummary("iron");
        rs.setBalance(20.34);
        rs.setIncome(12.34);
        obsList.add(rs);

        table.setItems(obsList);
    }


    @Override
    public void gameEvent(GameEvent gameEvent) {

    }
}
