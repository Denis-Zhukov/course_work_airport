package com.scenes.MaintenanceDispatcherPanel.DeleteSeatLayoutTemplatePanel;

import com.App;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.InteractingWithWindow;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.util.List;

public class DeleteSeatLayoutTemplatePanel {
    public static void showModal() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(DeleteSeatLayoutTemplatePanel.class.getResource("DeleteSeatLayoutTemplatePanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        try {
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
