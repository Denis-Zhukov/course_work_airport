package com.scenes.TrafficCoordinationDispatcherPanel.Countries.DeleteCountryPanel;

import com.App;
import com.assets.components.AutoCompleteComboBoxListener;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.Requests;
import com.scenes.GeneralScenes.ModalWindow.ModalWindow;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class DeleteCountryController {
    public ComboBox<String> countriesComboBox;

    public void submit() {
        String country = countriesComboBox.getValue();
        country = country == null ? "" : country;

        //Validation data
        if (!DeleteCountryPanel.countryId.containsKey(country)) {
            ModalWindow.show("Error", "A country has been selected that is not in the dropdown list\n", ModalWindow.Icon.error);
            return;
        }

        //API Request
        try {
            int id = DeleteCountryPanel.countryId.get(country);
            Requests.deleteCountry(id, App.getAccessToken());

            //Reset and update combobox
            DeleteCountryPanel.countryId.remove(country);
            countriesComboBox.getItems().remove(country);
            countriesComboBox.setValue("");

            ModalWindow.show("Success", "Country has deleted", ModalWindow.Icon.success);
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage() + "\nCountry has not deleted", ModalWindow.Icon.error);
        }
    }

    @FXML
    public void initialize() {
        new AutoCompleteComboBoxListener<>(countriesComboBox);
    }
}
