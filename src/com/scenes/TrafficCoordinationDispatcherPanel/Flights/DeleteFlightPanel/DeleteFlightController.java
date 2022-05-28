package com.scenes.TrafficCoordinationDispatcherPanel.Flights.DeleteFlightPanel;

import com.App;
import com.assets.components.AutoCompleteComboBoxListener;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.Requests;
import com.scenes.GeneralScenes.ModalWindow.ModalWindow;
import javafx.scene.control.ComboBox;

public class DeleteFlightController {
    public ComboBox<String> flightsComboBox;

    public void submit() {
        String flight = flightsComboBox.getValue();
        flight = flight == null ? "" : flight;

        //Validation data
        String error = "";
        if (!DeleteFlightPanel.flightsId.containsKey(flight))
            error += "Invalid flight selected\n";
        if (!error.equals("")) {
            ModalWindow.show("Error", error, ModalWindow.Icon.error);
            return;
        }

        //API Request
        try {
            int id = DeleteFlightPanel.flightsId.get(flight);
            Requests.deleteFlight(id, App.getAccessToken());

            //Reset and update combobox
            DeleteFlightPanel.flightsId.remove(flight);
            flightsComboBox.getItems().remove(flight);
            flightsComboBox.setValue("");

            ModalWindow.show("Success", "Price has set", ModalWindow.Icon.success);
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage() + "\nFlight has not set", ModalWindow.Icon.error);
        }
    }

    public void initialize() {
        new AutoCompleteComboBoxListener<>(flightsComboBox);
    }
}
