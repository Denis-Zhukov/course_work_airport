package com.scenes.TrafficCoordinationDispatcherPanel.Airports.DeleteAirportPanel;

import com.App;
import com.assets.components.AutoCompleteComboBoxListener;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.Requests;
import com.scenes.GeneralScenes.ModalWindow.ModalWindow;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class DeleteAirportController {
    @FXML
    private ComboBox<String> airportsComboBox;
    @FXML
    private TextField cityTextField;

    public void submit() {
        String airport = airportsComboBox.getValue();
        airport = airport == null ? "" : airport;

        //Validation data
        String error = "";
        if (!DeleteAirportPanel.airportsId.containsKey(airport))
            error += "A airport has been selected that is not in the dropdown list\n";
        if (!error.equals("")) {
            ModalWindow.show("Error", error, ModalWindow.Icon.error);
            return;
        }

        //API Request
        try {
            int idAirport = DeleteAirportPanel.airportsId.get(airport);
            Requests.deleteAirport(idAirport, App.getAccessToken());

            //Reset fields and update combobox
            DeleteAirportPanel.airportsId.remove(airport);
            airportsComboBox.getItems().remove(airport);
            airportsComboBox.setValue("");
            cityTextField.setText("");

            ModalWindow.show("Success", "Airport has deleted", ModalWindow.Icon.success);
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage() + "\nAirport has not deleted", ModalWindow.Icon.error);
        }
    }

    public void preloadCity() {
        String airport = airportsComboBox.getValue();
        airport = airport == null ? "" : airport;
        if (!DeleteAirportPanel.airportsId.containsKey(airport))
            return;

        try {
            String city = Requests.getAirport(DeleteAirportPanel.airportsId.get(airport), App.getAccessToken());
            cityTextField.setText(city);
        } catch (ResponseException | NoServerResponseException e) {
            ModalWindow.show("Error", "Failed to load airport data\nServer:" + e.getSuspendedMessage(), ModalWindow.Icon.error);
        }
    }

    public void initialize(){
        new AutoCompleteComboBoxListener<>(airportsComboBox);
    }
}
