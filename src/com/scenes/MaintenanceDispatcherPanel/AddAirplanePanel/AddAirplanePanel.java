package com.scenes.MaintenanceDispatcherPanel.AddAirplanePanel;

import com.App;
import com.assets.services.InteractingWithWindow;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.util.List;

public class AddAirplanePanel {
    public static void showModal() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(AddAirplanePanel.class.getResource("AddAirplanePanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        List<Integer> ids = Requests.getIdSeatingLayouts(App.getAccessToken());
        if (ids == null) {
            ModalWindow.show("Error", "Error getting data\n", ModalWindow.Icon.error);
            ((Stage) stage.getScene().getWindow()).close();
            return;
        }
        ((ComboBox) stage.getScene().lookup("#seatingLayoutComboBox")).getItems().addAll(ids);

        stage.show();
    }
}
