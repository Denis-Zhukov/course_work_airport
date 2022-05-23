package com.scenes.GeneralScenes.ShowAllFlightsPanel;

import com.assets.services.TableRows.AllFlightsRow;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowAllFlightsController {
    @FXML private TableColumn planeNumber;
    @FXML private TableColumn fromCountry, fromCity, fromAirport;
    @FXML private TableColumn toCountry, toCity, toAirport;
    @FXML private TableColumn dateTime;

    @FXML
    public void initialize(){
        planeNumber.setCellValueFactory(new PropertyValueFactory<AllFlightsRow, String>("number"));

        fromCountry.setCellValueFactory(new PropertyValueFactory<AllFlightsRow, String>("fromCountry"));
        fromCity.setCellValueFactory(new PropertyValueFactory<AllFlightsRow, String>("fromCity"));
        fromAirport.setCellValueFactory(new PropertyValueFactory<AllFlightsRow, String>("fromAirport"));

        toCountry.setCellValueFactory(new PropertyValueFactory<AllFlightsRow, String>("toCountry"));
        toCity.setCellValueFactory(new PropertyValueFactory<AllFlightsRow, String>("toCity"));
        toAirport.setCellValueFactory(new PropertyValueFactory<AllFlightsRow, String>("toAirport"));


        dateTime.setCellValueFactory(new PropertyValueFactory<AllFlightsRow, String>("boardingTime"));
    }
}
