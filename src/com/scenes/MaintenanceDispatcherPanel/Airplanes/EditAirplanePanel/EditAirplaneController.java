package com.scenes.MaintenanceDispatcherPanel.Airplanes.EditAirplanePanel;

import com.App;
import com.assets.components.AutoCompleteComboBoxListener;
import com.assets.services.Constants;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.Helpers.Airplane;
import com.assets.services.Helpers.SeatingLayout;
import com.assets.services.Requests;
import com.scenes.GeneralScenes.ModalWindow.ModalWindow;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class EditAirplaneController {
    @FXML
    private ComboBox<String> airplaneNumberComboBox;
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


    public void submit() {
        String airplaneNumber = airplaneNumberComboBox.getValue();
        airplaneNumber = airplaneNumber == null ? "" : airplaneNumber;

        Integer seatingLayout = seatingLayoutComboBox.getValue();
        seatingLayout = seatingLayout == null ? -1 : seatingLayout;

        String nameAirplane = nameAirplaneTextBox.getText();
        String newAirplaneNumber = numberAirplaneTextBox.getText();

        //Validation data
        String error = "";
        if (!EditAirplanePanel.airplaneNumbersId.containsKey(airplaneNumber))
            error += error += "Incorrect airplane number\n";
        ;
        if (!seatingLayoutComboBox.getItems().contains(seatingLayout))
            error += error += "Incorrect seating layout id\n";
        ;
        if (!Constants.regexAirplaneName.matcher(nameAirplane).find())
            error += "Incorrect airplane name\n";
        ;
        if (!Constants.regexAirplaneNumber.matcher(newAirplaneNumber).find())
            error += "Incorrect airplane number\n";

        if (!error.equals("")) {
            ModalWindow.show("Error", error, ModalWindow.Icon.error);
            return;
        }

        //API Request
        try {
            Requests.updateAirplane(
                    EditAirplanePanel.airplaneNumbersId.get(airplaneNumber),
                    seatingLayout,
                    nameAirplane,
                    newAirplaneNumber,
                    App.getAccessToken()
            );

            //Reset fields and combobox
            airplaneNumberComboBox.setValue("");
            seatingLayoutComboBox.setValue(null);
            nameAirplaneTextBox.setText("");
            numberAirplaneTextBox.setText("");
            firstClassCountRows.setText("0");
            firstClassCountCols.setText("0");
            businessClassCountRows.setText("0");
            businessClassCountCols.setText("0");
            economyClassCountRows.setText("0");
            economyClassCountCols.setText("0");
            EditAirplanePanel.airplaneNumbersId.put(newAirplaneNumber, EditAirplanePanel.airplaneNumbersId.get(airplaneNumber));
            EditAirplanePanel.airplaneNumbersId.remove(airplaneNumber);

            var items = airplaneNumberComboBox.getItems();
            items.remove(airplaneNumber);
            items.add(newAirplaneNumber);

            ModalWindow.show("Success", "Airplane has edited", ModalWindow.Icon.success);
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage() + "\nAirplane has not edited", ModalWindow.Icon.error);
        }
    }


    public void preloadAirplane() {
        String airplaneNumber = airplaneNumberComboBox.getValue();
        if (airplaneNumber.equals("")) return;
        if (!EditAirplanePanel.airplaneNumbersId.containsKey(airplaneNumber)) {
            ModalWindow.show("Error", "Invalid airplane number selected.\n", ModalWindow.Icon.error);
            return;
        }

        try {
            Airplane airplane = Requests.getAirplaneById(EditAirplanePanel.airplaneNumbersId.get(airplaneNumber), App.getAccessToken());
            if (seatingLayoutComboBox.getValue() == null) seatingLayoutComboBox.setValue(airplane.getIdSeatingLayout());
            if (nameAirplaneTextBox.getText().equals("")) nameAirplaneTextBox.setText(airplane.getName());
            if (numberAirplaneTextBox.getText().equals("")) numberAirplaneTextBox.setText(airplane.getNumber());
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage() + "\nAirplane has not removed.\nTry again.", ModalWindow.Icon.error);
            ((Stage) airplaneNumberComboBox.getScene().getWindow()).close();
        }
    }

    Map<String, SeatingLayout> seatingLayouts = new HashMap<>();
    public void preloadClasses() {
        Integer id = seatingLayoutComboBox.getValue();
        if (id == null) {
            return;
        }

        seatingLayouts.clear();
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

    public void initialize(){
        new AutoCompleteComboBoxListener<>(airplaneNumberComboBox);
    }
}
