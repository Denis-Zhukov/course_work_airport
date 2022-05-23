package com.scenes.TrafficCoordinationDispatcherPanel.EditRoutePanel;

import com.App;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.InteractingWithWindow;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import com.scenes.TrafficCoordinationDispatcherPanel.AddRoutePanel.AddRoutePanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.util.Map;

public class EditRoutePanel {
    static Map<String,Integer> airportsId;
    static Map<String,Integer> routesId;

    public static void showModal() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(EditRoutePanel.class.getResource("EditRoutePanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        try {
            airportsId = Requests.getAirportsCitiesCountries();
            ((ComboBox)stage.getScene().lookup("#fromComboBox")).getItems().addAll(airportsId.keySet());
            ((ComboBox)stage.getScene().lookup("#toComboBox")).getItems().addAll(airportsId.keySet());

            routesId = Requests.getAllRoutesWithId(App.getAccessToken());
            ((ComboBox)stage.getScene().lookup("#numberRouteComboBox")).getItems().addAll(routesId.keySet());

        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage(), ModalWindow.Icon.error);
            ((Stage) stage.getScene().getWindow()).close();
            return;
        }

        stage.show();
    }
}
