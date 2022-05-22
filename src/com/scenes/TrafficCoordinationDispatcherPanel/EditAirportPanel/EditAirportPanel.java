package com.scenes.TrafficCoordinationDispatcherPanel.EditAirportPanel;

import com.App;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.InteractingWithWindow;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.util.Map;

public class EditAirportPanel {
    static Map<String, Integer> citiesId;
    static Map<String, Integer> airportsId;

    public static void showModal() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(EditAirportPanel.class.getResource("EditAirportPanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        try {
            citiesId = Requests.getAllCities(App.getAccessToken());
            ((ComboBox<String>) stage.getScene().lookup("#citiesComboBox")).getItems().addAll(citiesId.keySet());
            airportsId = Requests.getAirports(App.getAccessToken());
            ((ComboBox<String>) stage.getScene().lookup("#airportsComboBox")).getItems().addAll(airportsId.keySet());
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage(), ModalWindow.Icon.error);
            ((Stage) stage.getScene().getWindow()).close();
            return;
        }

        stage.show();
    }
}
