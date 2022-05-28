package com.scenes.TrafficCoordinationDispatcherPanel.Routes.AddRoutePanel;

import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.InteractingWithWindow;
import com.assets.services.Requests;
import com.scenes.GeneralScenes.ModalWindow.ModalWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.util.Map;

public class AddRoutePanel {
    static Map<String,Integer> airportsId;

    public static void showModal() {
        //Open needed window
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(AddRoutePanel.class.getResource("AddRoutePanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        try {
            //Load all airports and their id
            airportsId = Requests.getAirportsCitiesCountries();
            ((ComboBox)stage.getScene().lookup("#fromComboBox")).getItems().setAll(airportsId.keySet());
            ((ComboBox)stage.getScene().lookup("#toComboBox")).getItems().setAll(airportsId.keySet());
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage(), ModalWindow.Icon.error);
            ((Stage) stage.getScene().getWindow()).close();
            return;
        }

        stage.show();
    }
}
