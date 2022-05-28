package com.scenes.TrafficCoordinationDispatcherPanel.Airports.AddAirportPanel;

import com.App;
import com.assets.components.AutoCompleteComboBoxListener;
import com.assets.services.Constants;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.Requests;
import com.scenes.GeneralScenes.ModalWindow.ModalWindow;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddAirportController {
    @FXML
    private ComboBox<String> citiesComboBox;
    @FXML
    private TextField airportNameTextField;

    public void submit() {
        String city = citiesComboBox.getValue();
        city = city == null ? "" : city;
        String airport = airportNameTextField.getText().trim();

        //Validation data
        String error = "";
        if (!AddAirportPanel.citiesId.containsKey(city))
            error += "A city has been selected that is not in the dropdown list\n";
        if (!Constants.regexCountryCityAirportName.matcher(airport).find())
            error += "Invalid airport name entered\n";
        if (!error.equals("")) {
            ModalWindow.show("Error", error, ModalWindow.Icon.error);
            return;
        }

        //API Request
        try {
            int id = AddAirportPanel.citiesId.get(city);
            Requests.addAirport(id, airport, App.getAccessToken());

            //Reset field and combobox
            citiesComboBox.setValue("");
            airportNameTextField.setText("");

            ModalWindow.show("Success", "Airport has added", ModalWindow.Icon.success);
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage() + "\nAirport has not added", ModalWindow.Icon.error);
        }
    }

    public void initialize(){
        new AutoCompleteComboBoxListener<>(citiesComboBox);
    }
}
