package com.scenes.TrafficCoordinationDispatcherPanel.Routes.EditRoutePanel;

import com.App;
import com.assets.components.AutoCompleteComboBoxListener;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.Requests;
import com.scenes.GeneralScenes.ModalWindow.ModalWindow;
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

        //Validation data
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

        //API Request
        try {
            int idRoute = EditRoutePanel.routesId.get(currentRoute);
            int newIdFrom = EditRoutePanel.airportsId.get(airportFrom);
            int newIdTo = EditRoutePanel.airportsId.get(airportTo);
            Requests.updateRoute(idRoute, newIdFrom, newIdTo, App.getAccessToken());

            ((Stage) fromComboBox.getScene().getWindow()).close(); //Hard to update data, cause close the window

            ModalWindow.show("Success", "Route has updated", ModalWindow.Icon.success);
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage() + "\nRoute has not updated", ModalWindow.Icon.error);
        }
    }

    public void initialize() {
        new AutoCompleteComboBoxListener<>(numberRouteComboBox);
        new AutoCompleteComboBoxListener<>(fromComboBox);
        new AutoCompleteComboBoxListener<>(toComboBox);
    }
}
