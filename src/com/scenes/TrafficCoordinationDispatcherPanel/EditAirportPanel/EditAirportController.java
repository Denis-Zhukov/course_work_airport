package com.scenes.TrafficCoordinationDispatcherPanel.EditAirportPanel;

import com.App;
import com.assets.components.AutoCompleteComboBoxListener;
import com.assets.services.Constants;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import com.scenes.TrafficCoordinationDispatcherPanel.DeleteCountryPanel.DeleteCountryPanel;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class EditAirportController {
    @FXML
    private ComboBox<String> airportsComboBox;
    @FXML
    private TextField newAirportNameTextField;
    @FXML
    private ComboBox<String> citiesComboBox;

    public void preloadCity() {
        String airport = airportsComboBox.getValue();
        airport = airport == null ? "" : airport.trim();
        if (!EditAirportPanel.airportsId.containsKey(airport))
            return;

        try {
            String city = Requests.getAirport(EditAirportPanel.airportsId.get(airport), App.getAccessToken());
            citiesComboBox.setValue(city);
            newAirportNameTextField.setText(airport);
        } catch (ResponseException | NoServerResponseException e) {
            ModalWindow.show("Error", "Failed to load airport data\nServer:" + e.getSuspendedMessage(), ModalWindow.Icon.error);
        }
    }

    public void submit() {
        String airport = airportsComboBox.getValue();
        airport = airport == null ? "" : airport.trim();
        String newAirport = newAirportNameTextField.getText().trim();
        String city = citiesComboBox.getValue();
        city = city == null ? "" : city.trim();

        String error = "";
        if (!EditAirportPanel.airportsId.containsKey(airport))
            error += "A airport has been selected that is not in the dropdown list\n";
        if (!Constants.regexCountryCityAirportName.matcher(newAirport).find())
            error += "Invalid new airport name entered\n";
        if (!EditAirportPanel.citiesId.containsKey(city))
            error += "A city has been selected that is not in the dropdown list\n";

        if (!error.equals("")) {
            ModalWindow.show("Error", error, ModalWindow.Icon.error);
            return;
        }

        try {
            int idAirport = EditAirportPanel.airportsId.get(airport);
            int idCity = EditAirportPanel.citiesId.get(city);
            Requests.updateAirport(idAirport, idCity, newAirport, App.getAccessToken());

            EditAirportPanel.airportsId.remove(airport);
            EditAirportPanel.airportsId.put(newAirport, idAirport);
            airportsComboBox.getItems().remove(airport);
            airportsComboBox.getItems().add(newAirport);
            airportsComboBox.setValue("");
            citiesComboBox.setValue("");

            ModalWindow.show("Success", "Airport has updated", ModalWindow.Icon.success);
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage() + "\nCountry has not deleted", ModalWindow.Icon.error);
        }
    }

    public void initialize() {
        new AutoCompleteComboBoxListener<>(airportsComboBox);
        new AutoCompleteComboBoxListener<>(citiesComboBox);
    }
}
