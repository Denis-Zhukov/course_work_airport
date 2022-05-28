package com.scenes.TrafficCoordinationDispatcherPanel.Routes.EditRoutePanel;

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

public class EditRoutePanel {
    static Map<String,Integer> airportsId;
    static Map<String,Integer> routesId;

    public static void showModal() {
        //Open needed window
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(EditRoutePanel.class.getResource("EditRoutePanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        try {
            //Load all airports and their id
            airportsId = Requests.getAirportsCitiesCountries();
            ((ComboBox)stage.getScene().lookup("#fromComboBox")).getItems().addAll(airportsId.keySet());
            ((ComboBox)stage.getScene().lookup("#toComboBox")).getItems().addAll(airportsId.keySet());
            //Load all routes and their id
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
