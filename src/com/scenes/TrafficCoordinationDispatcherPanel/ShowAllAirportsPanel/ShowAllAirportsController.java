package com.scenes.TrafficCoordinationDispatcherPanel.ShowAllAirportsPanel;

import com.assets.services.TableRows.AllRoutesRow;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowAllAirportsController {
    public TableColumn countryColumn;
    public TableColumn cityColumn;
    public TableColumn airportColumn;

    @FXML
    public void initialize() {
        countryColumn.setCellValueFactory(new PropertyValueFactory<AllRoutesRow, String>("country"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<AllRoutesRow, String>("city"));
        airportColumn.setCellValueFactory(new PropertyValueFactory<AllRoutesRow, String>("airport"));
    }
}
