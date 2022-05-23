package com.scenes.GeneralScenes.ShowAllFlightsPanel;

import com.App;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.InteractingWithWindow;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ShowAllFlightsPanel {
    public static void showModal() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(ShowAllFlightsPanel.class.getResource("ShowAllFlightsPanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        try {
            var data =Requests.getAllFlights(App.getAccessToken());
            ((TableView)stage.getScene().lookup("#table")).setItems(data);
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage(), ModalWindow.Icon.error);
            ((Stage) stage.getScene().getWindow()).close();
            return;
        }

        stage.show();
    }
}
