package com.scenes.MaintenanceDispatcherPanel.Airplanes.EditAirplanePanel;

import com.App;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.InteractingWithWindow;
import com.assets.services.Requests;
import com.scenes.GeneralScenes.ModalWindow.ModalWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;

public class EditAirplanePanel {
    static Map<String, Integer> airplaneNumbersId;

    public static void showModal() {
        //Open needed window
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(EditAirplanePanel.class.getResource("EditAirplanePanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        try {
            //Load all airplane numbers and their id
            airplaneNumbersId = Requests.getAirplaneNumbers(App.getAccessToken());
            ((ComboBox) stage.getScene().lookup("#airplaneNumberComboBox")).getItems().setAll(airplaneNumbersId.keySet());
            List<Integer> ids = Requests.getIdSeatingLayouts(App.getAccessToken());
            ((ComboBox) stage.getScene().lookup("#seatingLayoutComboBox")).getItems().setAll(ids);
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage(), ModalWindow.Icon.error);
            ((Stage) stage.getScene().getWindow()).close();
            return;
        }

        stage.show();
    }
}
