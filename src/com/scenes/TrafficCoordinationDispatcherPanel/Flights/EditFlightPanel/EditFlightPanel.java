package com.scenes.TrafficCoordinationDispatcherPanel.Flights.EditFlightPanel;

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

public class EditFlightPanel {
    static Map<String, Integer> routesId;
    static Map<String, Integer> airplaneNumbersId;
    static Map<String, Integer> flightsId;

    public static void showModal() {
        //Open needed window
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(EditFlightPanel.class.getResource("EditFlightPanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        try {
            //Load all flights and their id
            flightsId = Requests.getFlights(App.getAccessToken());
            ((ComboBox) stage.getScene().lookup("#flightsComboBox")).getItems().addAll(flightsId.keySet());
            //Load all routes and their id
            routesId = Requests.getAllRoutesWithId(App.getAccessToken());
            ((ComboBox) stage.getScene().lookup("#routesComboBox")).getItems().addAll(routesId.keySet());
            //Load all airplane and their id
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
