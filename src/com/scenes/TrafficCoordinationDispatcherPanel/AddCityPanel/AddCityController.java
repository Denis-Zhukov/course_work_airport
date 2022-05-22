package com.scenes.TrafficCoordinationDispatcherPanel.AddCityPanel;

import com.App;
import com.assets.components.AutoCompleteComboBoxListener;
import com.assets.services.Constants;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddCityController {
    public ComboBox<String> countriesComboBox;
    public TextField cityTextBox;

    public void submit() {
        String country = countriesComboBox.getValue();
        country = country == null ? "" : country.trim();
        String city = cityTextBox.getText();

        String error = "";
        if (!AddCityPanel.countryId.containsKey(country))
            error += "A country has been selected that is not in the dropdown list\n";
        if (!Constants.regexCountryCityAirportName.matcher(city).find())
            error += "Incorrect city name entered\n";

        if (!error.equals("")) {
            ModalWindow.show("Error", error, ModalWindow.Icon.error);
            return;
        }

        try {
            Requests.addCity(city, AddCityPanel.countryId.get(country), App.getAccessToken());
            countriesComboBox.setValue("");
            cityTextBox.setText("");
            ModalWindow.show("Success", "City has added", ModalWindow.Icon.success);
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage() + "\nCity has not added", ModalWindow.Icon.error);
        }
    }

    @FXML
    public void initialize() {
        new AutoCompleteComboBoxListener<>(countriesComboBox);
    }
}
