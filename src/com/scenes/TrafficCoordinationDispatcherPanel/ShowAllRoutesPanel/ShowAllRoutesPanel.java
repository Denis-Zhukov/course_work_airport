package com.scenes.TrafficCoordinationDispatcherPanel.ShowAllRoutesPanel;

import com.App;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.InteractingWithWindow;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ShowAllRoutesPanel {
    public static void showModal() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(ShowAllRoutesPanel.class.getResource("ShowAllRoutesPanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        try {
            ((TableView)stage.getScene().lookup("#table")).setItems(Requests.getAllRoutes(App.getAccessToken()));
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage(), ModalWindow.Icon.error);
            ((Stage) stage.getScene().getWindow()).close();
            return;
        }

        stage.show();
    }
}
