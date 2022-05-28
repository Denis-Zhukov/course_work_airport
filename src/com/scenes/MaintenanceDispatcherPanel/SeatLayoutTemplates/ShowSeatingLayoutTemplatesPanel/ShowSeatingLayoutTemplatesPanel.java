package com.scenes.MaintenanceDispatcherPanel.SeatLayoutTemplates.ShowSeatingLayoutTemplatesPanel;

import com.App;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.InteractingWithWindow;
import com.assets.services.Requests;
import com.scenes.GeneralScenes.ModalWindow.ModalWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ShowSeatingLayoutTemplatesPanel {
    public static void showModal() {
        //Open needed window
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(ShowSeatingLayoutTemplatesPanel.class.getResource("ShowSeatingLayoutTemplatesPanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        TableView table = (TableView) stage.getScene().lookup("#table");

        try {
            //Load all id of layouts and shot their
            table.getItems().setAll(Requests.getAllSeatingLayoutTemplates(App.getAccessToken()));
        } catch (ResponseException | NoServerResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage(), ModalWindow.Icon.error);
            ((Stage) stage.getScene().getWindow()).close();
            return;
        }

        stage.show();
    }
}
