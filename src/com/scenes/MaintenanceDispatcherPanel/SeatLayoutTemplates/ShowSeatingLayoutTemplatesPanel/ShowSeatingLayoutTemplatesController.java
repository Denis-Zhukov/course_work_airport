package com.scenes.MaintenanceDispatcherPanel.SeatLayoutTemplates.ShowSeatingLayoutTemplatesPanel;

import com.assets.services.TableRows.SeatingLayoutRow;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowSeatingLayoutTemplatesController {
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
