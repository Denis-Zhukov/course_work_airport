package com.scenes.MaintenanceDispatcherPanel.ShowSeatingLayoutTemplatesPanel;

import com.assets.services.SeatingLayoutRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowSeatingLayoutTemplatesController {
    @FXML
    private TableView table;
    @FXML
    private TableColumn templateNumberColumn;
    @FXML
    private TableColumn ecoRowsColumn, ecoColsColumn;
    @FXML
    private TableColumn busRowsColumn, busColsColumn;
    @FXML
    private TableColumn firstRowsColumn, firstColsColumn;

    @FXML
    public void initialize() {
        templateNumberColumn.setCellValueFactory(new PropertyValueFactory<SeatingLayoutRow, String>("id"));

        ecoRowsColumn.setCellValueFactory(new PropertyValueFactory<SeatingLayoutRow, String>("ecoRows"));
        ecoColsColumn.setCellValueFactory(new PropertyValueFactory<SeatingLayoutRow, String>("ecoCols"));

        busRowsColumn.setCellValueFactory(new PropertyValueFactory<SeatingLayoutRow, String>("busRows"));
        busColsColumn.setCellValueFactory(new PropertyValueFactory<SeatingLayoutRow, String>("busCols"));

        firstRowsColumn.setCellValueFactory(new PropertyValueFactory<SeatingLayoutRow, String>("firstRows"));
        firstColsColumn.setCellValueFactory(new PropertyValueFactory<SeatingLayoutRow, String>("firstCols"));
    }
}
