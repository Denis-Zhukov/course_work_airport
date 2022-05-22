package com.scenes.TrafficCoordinationDispatcherPanel.EditRoutePanel;

import com.App;
import com.assets.components.AutoCompleteComboBoxListener;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import com.scenes.TrafficCoordinationDispatcherPanel.AddRoutePanel.AddRoutePanel;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class EditRouteController {
    public ComboBox<String> numberRouteComboBox;
    public ComboBox<String> fromComboBox;
    public ComboBox<String> toComboBox;

    public void submit() {
        String airportFrom = fromComboBox.getValue();
        airportFrom = airportFrom == null ? "" : airportFrom;
        String airportTo = toComboBox.getValue();
        airportTo = airportTo == null ? "" : airportTo;
        String currentRoute = numberRouteComboBox.getValue();
        currentRoute = currentRoute == null ? "" : currentRoute;

        String error = "";
        if (!EditRoutePanel.airportsId.containsKey(airportFrom))
            error += "Invalid departure airport selected\n";
        if (!EditRoutePanel.airportsId.containsKey(airportTo))
            error += "Invalid destination airport selected\n";
        if (!EditRoutePanel.routesId.containsKey(currentRoute))
            error += "Invalid route selected\n";
        if (airportFrom.equals(airportTo))
            error += "Departure airport and destination airport are identical";

        if (!error.equals("")) {
            ModalWindow.show("Error", error, ModalWindow.Icon.error);
            return;
        }

        try {
            int idRoute = EditRoutePanel.routesId.get(currentRoute);
            int newIdFrom = EditRoutePanel.airportsId.get(airportFrom);
            int newIdTo = EditRoutePanel.airportsId.get(airportTo);
            Requests.updateRoute(idRoute, newIdFrom, newIdTo, App.getAccessToken());
            ModalWindow.show("Success", "Route has edited", ModalWindow.Icon.success);
            ((Stage) fromComboBox.getScene().getWindow()).close();
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage() + "\nRoute has not edited", ModalWindow.Icon.error);
        }
    }

    public void initialize() {
        new AutoCompleteComboBoxListener<>(numberRouteComboBox);
        new AutoCompleteComboBoxListener<>(fromComboBox);
        new AutoCompleteComboBoxListener<>(toComboBox);
    }
}
