package com.scenes.MaintenanceDispatcherPanel.Airplanes.AddAirplanePanel;

import com.App;
import com.assets.services.Constants;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.Helpers.SeatingLayout;
import com.assets.services.Requests;
import com.scenes.GeneralScenes.ModalWindow.ModalWindow;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;

public class AddAirplaneController {

    @FXML
    private ComboBox<Integer> seatingLayoutComboBox;
    @FXML
    private Label firstClassCountRows, firstClassCountCols;
    @FXML
    private Label businessClassCountRows, businessClassCountCols;
    @FXML
    private Label economyClassCountRows, economyClassCountCols;
    @FXML
    private TextField nameAirplaneTextBox, numberAirplaneTextBox;

    Map<String, SeatingLayout> seatingLayout = new HashMap<>();

    public void submit() {
        Integer seatingLayoutId = seatingLayoutComboBox.getValue();
        seatingLayoutId = seatingLayoutId == null ? -1 : seatingLayoutId;

        String nameAirplane = nameAirplaneTextBox.getText();
        String numberAirplane = numberAirplaneTextBox.getText();

        //Validation data
        String error = "";
        if (!seatingLayoutComboBox.getItems().contains(seatingLayoutId))
            error += "Incorrect seating layout id.\n";
        if (!Constants.regexAirplaneName.matcher(nameAirplane).find())
            error += "Incorrect name of airplane.\n";
        if (!Constants.regexAirplaneNumber.matcher(numberAirplane).find())
            error += "Incorrect number of airplane.\n";

        if (!error.equals("")) {
            ModalWindow.show("Error", error, ModalWindow.Icon.error);
            return;
        }

        //API Request
        try {
            Requests.addAirplane(seatingLayoutId, nameAirplane, numberAirplane, App.getAccessToken());

            //Reset fields and combobox
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
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage() + "\nAirplane has not added", ModalWindow.Icon.error);
        }
    }

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
            firstClassCountRows.setText("Invalid template data");
            firstClassCountCols.setText("Invalid template data");
        }
        if (layout.containsKey("Business")) {
            businessClassCountRows.setText(Integer.toString(layout.get("Business").getCountRows()));
            businessClassCountCols.setText(Integer.toString(layout.get("Business").getCountCols()));
        } else {
            businessClassCountRows.setText("Invalid template data");
            businessClassCountCols.setText("Invalid template data");
        }
        if (layout.containsKey("Economy")) {
            economyClassCountRows.setText(Integer.toString(layout.get("Economy").getCountRows()));
            economyClassCountCols.setText(Integer.toString(layout.get("Economy").getCountCols()));
        } else {
            economyClassCountRows.setText("Invalid template data");
            economyClassCountCols.setText("Invalid template data");
        }
    }

}
