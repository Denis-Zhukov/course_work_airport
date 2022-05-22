package com.scenes.TrafficCoordinationDispatcherPanel.DeleteRoutePanel;

import com.App;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import com.scenes.TrafficCoordinationDispatcherPanel.EditRoutePanel.EditRoutePanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class DeleteRouteController {
    @FXML
    private ComboBox<String> routesComboBox;

    public void submit() {
        String route = routesComboBox.getValue();
        route = route == null ? "" : route;

        String error = "";
        if (!DeleteRoutePanel.routesId.containsKey(route))
            error += "Invalid route selected\n";

        if (!error.equals("")) {
            ModalWindow.show("Error", error, ModalWindow.Icon.error);
            return;
        }

        try {
            Requests.deleteRoute(DeleteRoutePanel.routesId.get(route), App.getAccessToken());
            DeleteRoutePanel.routesId.remove(route);
            routesComboBox.getItems().remove(route);
            routesComboBox.setValue("");
            ModalWindow.show("Error", "Route has deleted", ModalWindow.Icon.success);
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage() + "\nRoute has not deleted", ModalWindow.Icon.error);
        }
    }
}
