package com.scenes.TrafficCoordinationDispatcherPanel.AddRoutePanel;

import com.App;
import com.assets.components.AutoCompleteComboBoxListener;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class AddRouteController {
    @FXML
    private ComboBox<String> fromComboBox, toComboBox;

    public void submit() {
        String airportFrom = fromComboBox.getValue();
        airportFrom = airportFrom == null ? "" : airportFrom;
        String airportTo = toComboBox.getValue();
        airportTo = airportTo == null ? "" : airportTo;

        String error = "";
        if (!AddRoutePanel.airportsId.containsKey(airportFrom))
            error += "Invalid departure airport selected\n";
        if (!AddRoutePanel.airportsId.containsKey(airportTo))
            error += "Invalid destination airport selected\n";
        if (airportFrom.equals(airportTo))
            error += "Departure airport and destination airport are identical";

        if (!error.equals("")) {
            ModalWindow.show("Error", error, ModalWindow.Icon.error);
            return;
        }

        try {
            Requests.addRoute(AddRoutePanel.airportsId.get(airportFrom), AddRoutePanel.airportsId.get(airportTo), App.getAccessToken());
            fromComboBox.setValue("");
            toComboBox.setValue("");
            ModalWindow.show("Success", "Route has added", ModalWindow.Icon.success);
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage() + "\nRoute has not added", ModalWindow.Icon.error);
        }
    }

    @FXML
    public void initialize(){
        new AutoCompleteComboBoxListener<>(fromComboBox);
        new AutoCompleteComboBoxListener<>(toComboBox);
    }
}
