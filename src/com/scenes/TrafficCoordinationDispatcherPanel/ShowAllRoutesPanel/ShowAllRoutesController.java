package com.scenes.TrafficCoordinationDispatcherPanel.ShowAllRoutesPanel;

import com.assets.services.TableRows.AllRoutesRow;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowAllRoutesController {
    @FXML
    private TableColumn fromAirportColumn, fromCityColumn, fromCountryColumn;
    @FXML
    private TableColumn toAirportColumn, toCityColumn, toCountryColumn;

    @FXML
    public void initialize() {
        fromAirportColumn.setCellValueFactory(new PropertyValueFactory<AllRoutesRow, String>("fromAirport"));
        fromCityColumn.setCellValueFactory(new PropertyValueFactory<AllRoutesRow, String>("fromCity"));
        fromCountryColumn.setCellValueFactory(new PropertyValueFactory<AllRoutesRow, String>("fromCountry"));

        toAirportColumn.setCellValueFactory(new PropertyValueFactory<AllRoutesRow, String>("toAirport"));
        toCityColumn.setCellValueFactory(new PropertyValueFactory<AllRoutesRow, String>("toCity"));
        toCountryColumn.setCellValueFactory(new PropertyValueFactory<AllRoutesRow, String>("toCountry"));
    }
}
