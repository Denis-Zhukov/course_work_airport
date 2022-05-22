package com.scenes.TrafficCoordinationDispatcherPanel.EditCountryPanel;

import com.App;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.InteractingWithWindow;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import com.scenes.TrafficCoordinationDispatcherPanel.ShowAllAirportsPanel.ShowAllAirportsPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.Map;

public class EditCountryPanel {
    static Map<String, Integer> countryId;

    public static void showModal() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(EditCountryPanel.class.getResource("EditCountryPanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        try {
            countryId = Requests.getCountries(App.getAccessToken());
            ((ComboBox<String>)stage.getScene().lookup("#countriesComboBox")).getItems().addAll(countryId.keySet());
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage(), ModalWindow.Icon.error);
            ((Stage) stage.getScene().getWindow()).close();
            return;
        }

        stage.show();
    }
}
