package com.scenes.MaintenanceDispatcherPanel.DeleteAirplanePanel;

import com.App;
import com.assets.components.AutoCompleteComboBoxListener;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.InteractingWithWindow;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.util.Map;


public class DeleteAirplanePanel {
    static Map<String, Integer> airplaneNumbersId;

    public static void showModal() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(DeleteAirplanePanel.class.getResource("DeleteAirplanePanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        try {
            airplaneNumbersId = Requests.getAirplaneNumbers(App.getAccessToken());
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage(), ModalWindow.Icon.error);
            ((Stage) stage.getScene().getWindow()).close();
            return;
        }

        ComboBox<String> numbersCB = ((ComboBox)stage.getScene().lookup("#airplaneNumberComboBox"));
        numbersCB.getItems().addAll(airplaneNumbersId.keySet());
        new AutoCompleteComboBoxListener<>(numbersCB);

        stage.show();
    }
}
