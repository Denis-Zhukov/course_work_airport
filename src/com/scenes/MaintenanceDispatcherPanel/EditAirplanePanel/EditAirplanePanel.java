package com.scenes.MaintenanceDispatcherPanel.EditAirplanePanel;

import com.App;
import com.assets.services.AutoCompleteComboBoxListener;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.InteractingWithWindow;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;

public class EditAirplanePanel {
    static Map<String, Integer> airplaneNumbersId;

    public static void showModal() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(EditAirplanePanel.class.getResource("EditAirplanePanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        try {
            airplaneNumbersId = Requests.getAirplaneNumbers(App.getAccessToken());
            ComboBox<String> numbersCB = ((ComboBox) stage.getScene().lookup("#airplaneNumberComboBox"));
            numbersCB.getItems().addAll(airplaneNumbersId.keySet());
            new AutoCompleteComboBoxListener<>(numbersCB);

            List<Integer> ids = Requests.getIdSeatingLayouts(App.getAccessToken());
            if (ids == null) {
                ModalWindow.show("Error", "Error getting data\n", ModalWindow.Icon.error);
                ((Stage) stage.getScene().getWindow()).close();
                return;
            }
            ((ComboBox) stage.getScene().lookup("#seatingLayoutComboBox")).getItems().addAll(ids);
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage(), ModalWindow.Icon.error);
            ((Stage) stage.getScene().getWindow()).close();
            return;
        }

        stage.show();
    }
}
