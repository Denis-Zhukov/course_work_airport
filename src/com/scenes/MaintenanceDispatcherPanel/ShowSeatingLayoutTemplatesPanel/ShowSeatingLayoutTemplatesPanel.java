package com.scenes.MaintenanceDispatcherPanel.ShowSeatingLayoutTemplatesPanel;

import com.App;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.InteractingWithWindow;
import com.assets.services.Requests;
import com.assets.services.SeatingLayoutRow;
import com.scenes.MaintenanceDispatcherPanel.DeleteSeatLayoutTemplatePanel.DeleteSeatLayoutTemplatePanel;
import com.scenes.ModalWindow.ModalWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.List;

public class ShowSeatingLayoutTemplatesPanel {
    public static void showModal() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(ShowSeatingLayoutTemplatesPanel.class.getResource("ShowSeatingLayoutTemplatesPanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        TableView table = (TableView) stage.getScene().lookup("#table");

        try {
            table.getItems().addAll(Requests.getAllSeatingLayoutTemplates(App.getAccessToken()));
        } catch (ResponseException | NoServerResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage(), ModalWindow.Icon.error);
            ((Stage) stage.getScene().getWindow()).close();
            return;
        }

        stage.show();
    }
}
