package com.scenes.TrafficCoordinationDispatcherPanel.AddFlightPanel;

import com.App;
import com.assets.components.AutoCompleteComboBoxListener;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.InteractingWithWindow;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import com.scenes.TrafficCoordinationDispatcherPanel.AddCountryPanel.AddCountryPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.util.Map;

public class AddFlightPanel {
    static Map<String, Integer> routesId;
    static Map<String, Integer> airplaneNumbersId;

    public static void showModal() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(AddFlightPanel.class.getResource("AddFlightPanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        try {
            routesId = Requests.getAllRoutesWithId(App.getAccessToken());
            ((ComboBox)stage.getScene().lookup("#routesComboBox")).getItems().addAll(routesId.keySet());

            airplaneNumbersId = Requests.getAirplaneNumbers(App.getAccessToken());
            ((ComboBox)stage.getScene().lookup("#numbersPlaneComboBox")).getItems().addAll(airplaneNumbersId.keySet());
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage(), ModalWindow.Icon.error);
            ((Stage) stage.getScene().getWindow()).close();
            return;
        }

        stage.show();
    }
}
