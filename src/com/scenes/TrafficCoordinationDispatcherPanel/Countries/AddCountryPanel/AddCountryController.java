package com.scenes.TrafficCoordinationDispatcherPanel.Countries.AddCountryPanel;

import com.App;
import com.assets.services.Constants;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.Requests;
import com.scenes.GeneralScenes.ModalWindow.ModalWindow;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddCountryController {
    @FXML
    private TextField countryTextField;

    public void submit() {
        String country = countryTextField.getText().trim();

        //Validation data
        if (!Constants.regexCountryCityAirportName.matcher(country).find()) {
            ModalWindow.show("Error", "Invalid country name\n", ModalWindow.Icon.error);
            return;
        }

        //API Request
        try {
            Requests.addCountry(country, App.getAccessToken());

            //Reset field
            countryTextField.setText("");

            ModalWindow.show("Success", "Country has added", ModalWindow.Icon.success);
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage() + "\nCountry has not added", ModalWindow.Icon.error);
        }
    }
}
