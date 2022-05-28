package com.scenes.TrafficCoordinationDispatcherPanel.Airports.ShowAllAirportsPanel;

import com.assets.services.TableRows.AllRoutesRow;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowAllAirportsController {
    @FXML
    private TableColumn countryColumn, cityColumn, airportColumn;

    @FXML
    public void initialize() {
        countryColumn.setCellValueFactory(new PropertyValueFactory<AllRoutesRow, String>("country"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<AllRoutesRow, String>("city"));
        airportColumn.setCellValueFactory(new PropertyValueFactory<AllRoutesRow, String>("airport"));
    }
}
