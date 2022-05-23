package com.scenes.CustomerPanel;

import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.InteractingWithWindow;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.util.Map;

public class CustomerPanel {
    static Map<String, Integer> airportsId;

    public static void openChangingScene(Stage stage) {
        FXMLLoader loader = new FXMLLoader(CustomerPanel.class.getResource("CustomerPanel.fxml"));
        InteractingWithWindow.changeScene(stage, loader);
        stage.centerOnScreen();

        try{
            airportsId = Requests.getAirportsCitiesCountries();
            ((ComboBox<String>)stage.getScene().lookup("#fromComboBox")).getItems().addAll(airportsId.keySet());
            ((ComboBox<String>)stage.getScene().lookup("#toComboBox")).getItems().addAll(airportsId.keySet());
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage(), ModalWindow.Icon.error);
        }

        stage.show();
    }
}
