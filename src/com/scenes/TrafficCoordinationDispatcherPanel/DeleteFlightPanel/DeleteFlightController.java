package com.scenes.TrafficCoordinationDispatcherPanel.DeleteFlightPanel;

import com.App;
import com.assets.components.AutoCompleteComboBoxListener;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import com.scenes.TrafficCoordinationDispatcherPanel.EditFlightPanel.EditFlightPanel;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;

public class DeleteFlightController {
    public ComboBox<String> flightsComboBox;

    public void submit() {
        String flight = flightsComboBox.getValue();
        flight = flight == null ? "" : flight;

        String error = "";
        if (!DeleteFlightPanel.flightsId.containsKey(flight))
            error += "Invalid flight selected\n";

        if (!error.equals("")) {
            ModalWindow.show("Error", error, ModalWindow.Icon.error);
            return;
        }

        try {
            int id = DeleteFlightPanel.flightsId.get(flight);
            Requests.deleteFlight(id, App.getAccessToken());
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
