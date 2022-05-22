package com.scenes.TrafficCoordinationDispatcherPanel.AddCountryPanel;

import com.App;
import com.assets.services.Constants;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddCountryController {
    @FXML
    private TextField countryTextField;

    public void submit() {
        String country = countryTextField.getText();
        if (!Constants.regexCountryName.matcher(country).find()) {
            ModalWindow.show("Error", "Invalid country name entered\n", ModalWindow.Icon.error);
            return;
        }

        try {
            Requests.addCountry(country, App.getAccessToken());
            countryTextField.setText("");
            ModalWindow.show("Success", "Country has added", ModalWindow.Icon.success);
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage() + "\nCountry has not added", ModalWindow.Icon.error);
        }
    }
}
