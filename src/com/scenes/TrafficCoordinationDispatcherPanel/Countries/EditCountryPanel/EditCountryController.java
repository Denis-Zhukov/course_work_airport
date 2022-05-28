package com.scenes.TrafficCoordinationDispatcherPanel.Countries.EditCountryPanel;

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

public class EditCountryController {

    public ComboBox<String> countriesComboBox;
    public TextField newCountryNameTextField;

    public void submit() {
        String country = countriesComboBox.getValue();
        country = country == null ? "" : country;
        String newCountryName = newCountryNameTextField.getText().trim();

        //Validation data
        String error = "";
        if (!EditCountryPanel.countryId.containsKey(country))
            error += "A country has been selected that is not in the dropdown list\n";
        if (!Constants.regexCountryCityAirportName.matcher(newCountryName).find())
            error += "Incorrect country name entered\n";
        if (!error.equals("")) {
            ModalWindow.show("Error", error, ModalWindow.Icon.error);
            return;
        }

        //API Request
        try {
            int id = EditCountryPanel.countryId.get(country);
            Requests.updateCountry(id, newCountryName, App.getAccessToken());

            //Reset field and update combobox
            EditCountryPanel.countryId.remove(country);
            EditCountryPanel.countryId.put(newCountryName, id);
            countriesComboBox.getItems().remove(country);
            countriesComboBox.getItems().add(newCountryName);

            ModalWindow.show("Success", "Country has edited", ModalWindow.Icon.success);
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage() + "\nCountry has not edited", ModalWindow.Icon.error);
        }
    }

    @FXML
    public void initialize() {
        new AutoCompleteComboBoxListener<>(countriesComboBox);
    }
}
