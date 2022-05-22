package com.scenes.MaintenanceDispatcherPanel.DeleteSeatLayoutTemplatePanel;

import com.App;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.Requests;
import com.assets.services.SeatingLayout;
import com.scenes.MaintenanceDispatcherPanel.DeleteAirplanePanel.DeleteAirplanePanel;
import com.scenes.ModalWindow.ModalWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.HashMap;
import java.util.Map;

public class DeleteSeatLayoutTemplateController {
    @FXML
    ComboBox<Integer> seatingLayoutComboBox;
    @FXML
    Label firstClassCountRows, firstClassCountCols;
    @FXML
    Label businessClassCountRows, businessClassCountCols;
    @FXML
    Label economyClassCountRows, economyClassCountCols;

    Map<String, SeatingLayout> seatingLayout = new HashMap<>();

    public void preloadClasses() {
        Integer id = seatingLayoutComboBox.getValue();
        if (id == null) {
            return;
        }
        seatingLayout.clear();
        Map<String, SeatingLayout> layout = Requests.getSeatingLayout(id, App.getAccessToken());
        if (layout == null) {
            ModalWindow.show("Error", "Failed to get seating layout", ModalWindow.Icon.error);
            return;
        }
        if (layout.containsKey("First")) {
            firstClassCountRows.setText(Integer.toString(layout.get("First").getCountRows()));
            firstClassCountCols.setText(Integer.toString(layout.get("First").getCountCols()));
        } else {
            firstClassCountRows.setText("error");
            firstClassCountCols.setText("error");
        }
        if (layout.containsKey("Business")) {
            businessClassCountRows.setText(Integer.toString(layout.get("Business").getCountRows()));
            businessClassCountCols.setText(Integer.toString(layout.get("Business").getCountCols()));
        } else {
            businessClassCountRows.setText("error");
            businessClassCountCols.setText("error");
        }
        if (layout.containsKey("Economy")) {
            economyClassCountRows.setText(Integer.toString(layout.get("Economy").getCountRows()));
            economyClassCountCols.setText(Integer.toString(layout.get("Economy").getCountCols()));
        } else {
            economyClassCountRows.setText("error");
            economyClassCountCols.setText("error");
        }
    }

    public void submit() {
        Integer id = seatingLayoutComboBox.getValue();
        id = id == null ? -1 : id;

        if (!seatingLayoutComboBox.getItems().contains(id)) {
            ModalWindow.show("Error", "Invalid seating layout template", ModalWindow.Icon.error);
            return;
        }

        try {
            Requests.deleteSeatLayoutTemplate(id, App.getAccessToken());
            seatingLayoutComboBox.getItems().remove(id);
            seatingLayoutComboBox.setValue(null);

            firstClassCountRows.setText("0");
            firstClassCountCols.setText("0");

            businessClassCountRows.setText("0");
            businessClassCountCols.setText("0");

            economyClassCountRows.setText("0");
            economyClassCountCols.setText("0");

            ModalWindow.show("Success", "Template has removed.\n", ModalWindow.Icon.success);
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage() + "\nTemplate has not removed.\nTry again.", ModalWindow.Icon.error);
        }
    }
}
