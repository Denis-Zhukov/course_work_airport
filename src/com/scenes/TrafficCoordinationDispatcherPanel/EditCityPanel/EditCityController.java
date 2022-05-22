package com.scenes.TrafficCoordinationDispatcherPanel.EditCityPanel;

import com.App;
import com.assets.components.AutoCompleteComboBoxListener;
import com.assets.services.Constants;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class EditCityController {
    public ComboBox<String> citiesComboBox;
    public TextField newCityNameTextField;

    public void submit() {
        String city = citiesComboBox.getValue();
        city = city == null ? "" : city.trim();
        String newCityName = newCityNameTextField.getText().trim();

        String error = "";
        if (!EditCityPanel.citiesId.containsKey(city))
            error += "A city has been selected that is not in the dropdown list\n";
        if (!Constants.regexCountryCityAirportName.matcher(newCityName).find())
            error += "Incorrect country name entered\n";

        if (!error.equals("")) {
            ModalWindow.show("Error", error, ModalWindow.Icon.error);
            return;
        }

        try {
            int id = EditCityPanel.citiesId.get(city);
            Requests.updateCity(id, newCityName, App.getAccessToken());
            EditCityPanel.citiesId.remove(city);
            citiesComboBox.getItems().remove(city);

            String[] cityElements = city.split("\s");
            EditCityPanel.citiesId.put(newCityName + " " + cityElements[cityElements.length - 1], id);
            citiesComboBox.getItems().add(newCityName + " " + cityElements[cityElements.length - 1]);
            newCityNameTextField.setText("");
            ModalWindow.show("Success", "Country has edited", ModalWindow.Icon.success);
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage() + "\nCountry has not edited", ModalWindow.Icon.error);
        }
    }

    public void initialize() {
        new AutoCompleteComboBoxListener<>(citiesComboBox);
    }
}
