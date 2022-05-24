package com.scenes.TrafficCoordinationDispatcherPanel.EditFlightPanel;

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

public class EditFlightPanel {
    static Map<String, Integer> routesId;
    static Map<String, Integer> airplaneNumbersId;
    static Map<String, Integer> flightsId;

    public static void showModal() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(EditFlightPanel.class.getResource("EditFlightPanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        try {
            flightsId = Requests.getFlights(App.getAccessToken());
            ((ComboBox) stage.getScene().lookup("#flightsComboBox")).getItems().addAll(flightsId.keySet());

            routesId = Requests.getAllRoutesWithId(App.getAccessToken());
            ((ComboBox) stage.getScene().lookup("#routesComboBox")).getItems().addAll(routesId.keySet());

            airplaneNumbersId = Requests.getAirplaneNumbers(App.getAccessToken());
            ((ComboBox) stage.getScene().lookup("#planesComboBox")).getItems().addAll(airplaneNumbersId.keySet());
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage(), ModalWindow.Icon.error);
            ((Stage) stage.getScene().getWindow()).close();
            return;
        }

        stage.show();
    }
}
