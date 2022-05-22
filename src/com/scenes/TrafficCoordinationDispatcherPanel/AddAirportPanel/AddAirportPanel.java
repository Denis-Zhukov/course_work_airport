package com.scenes.TrafficCoordinationDispatcherPanel.AddAirportPanel;

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

public class AddAirportPanel {
    static Map<String, Integer> citiesId;

    public static void showModal() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(AddAirportPanel.class.getResource("AddAirportPanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        try {
            citiesId = Requests.getAllCities(App.getAccessToken());
            ((ComboBox<String>) stage.getScene().lookup("#citiesComboBox")).getItems().addAll(citiesId.keySet());
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage(), ModalWindow.Icon.error);
            ((Stage) stage.getScene().getWindow()).close();
            return;
        }

        stage.show();
    }
}
