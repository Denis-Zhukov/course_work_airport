package com.scenes.TrafficCoordinationDispatcherPanel.Airports.EditAirportPanel;

import com.App;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.InteractingWithWindow;
import com.assets.services.Requests;
import com.scenes.GeneralScenes.ModalWindow.ModalWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.util.Map;

public class EditAirportPanel {
    static Map<String, Integer> citiesId;
    static Map<String, Integer> airportsId;

    public static void showModal() {
        //Open needed window
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(EditAirportPanel.class.getResource("EditAirportPanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        try {
            //Load all cities and their id
            citiesId = Requests.getAllCities(App.getAccessToken());
            ((ComboBox<String>) stage.getScene().lookup("#citiesComboBox")).getItems().setAll(citiesId.keySet());
            //Load all airports and their id
            airportsId = Requests.getAirports(App.getAccessToken());
            ((ComboBox<String>) stage.getScene().lookup("#airportsComboBox")).getItems().setAll(airportsId.keySet());
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage(), ModalWindow.Icon.error);
            ((Stage) stage.getScene().getWindow()).close();
            return;
        }

        stage.show();
    }
}
