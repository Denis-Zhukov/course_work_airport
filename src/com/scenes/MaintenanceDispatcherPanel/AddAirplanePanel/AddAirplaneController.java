package com.scenes.MaintenanceDispatcherPanel.AddAirplanePanel;

import com.App;
import com.assets.services.Requests;
import com.assets.services.SeatingLayout;
import com.scenes.ModalWindow.ModalWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddAirplaneController {

    @FXML
    ComboBox<Integer> seatingLayoutComboBox;
    @FXML
    Label firstClassCountRows;
    @FXML
    Label firstClassCountCols;
    @FXML
    Label businessClassCountRows;
    @FXML
    Label businessClassCountCols;
    @FXML
    Label economyClassCountRows;
    @FXML
    Label economyClassCountCols;
    @FXML
    TextField nameAirplaneTextBox;
    @FXML
    TextField numberAirplaneTextBox;

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
            firstClassCountRows.setText("0");
            firstClassCountCols.setText("0");
        }
        if (layout.containsKey("Business")) {
            businessClassCountRows.setText(Integer.toString(layout.get("Business").getCountRows()));
            businessClassCountCols.setText(Integer.toString(layout.get("Business").getCountCols()));
        } else {
            businessClassCountRows.setText("0");
            businessClassCountCols.setText("0");
        }
        if (layout.containsKey("Economy")) {
            economyClassCountRows.setText(Integer.toString(layout.get("Economy").getCountRows()));
            economyClassCountCols.setText(Integer.toString(layout.get("Economy").getCountCols()));
        } else {
            economyClassCountRows.setText("0");
            economyClassCountCols.setText("0");
        }
    }

    public void submit() {
        int seatingLayoutId = seatingLayoutComboBox.getValue();
        String nameAirplane = nameAirplaneTextBox.getText();
        String numberAirplane = numberAirplaneTextBox.getText();

        String error = "";
        if(!seatingLayoutComboBox.getItems().contains(seatingLayoutId))
            error+="Incorrect seating layout id.\n";
        if(nameAirplane.equals(""))
            error+="Incorrect name of airplane.\n";
        if(numberAirplane.equals(""))
            error+="Incorrect number of airplane.\n";

        if(!error.equals("")){
            ModalWindow.show("Error", error, ModalWindow.Icon.error);
            return;
        }

        try {
            Requests.addAirplane(seatingLayoutId, nameAirplane, numberAirplane, App.getAccessToken());
            seatingLayoutComboBox.setValue(null);
            nameAirplaneTextBox.setText("");
            numberAirplaneTextBox.setText("");

            firstClassCountRows.setText("0");
            firstClassCountCols.setText("0");

            businessClassCountRows.setText("0");
            businessClassCountCols.setText("0");

            economyClassCountRows.setText("0");
            economyClassCountCols.setText("0");

            ModalWindow.show("Success", "Airplane has added", ModalWindow.Icon.success);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
