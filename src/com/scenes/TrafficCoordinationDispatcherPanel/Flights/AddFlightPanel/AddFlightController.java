package com.scenes.TrafficCoordinationDispatcherPanel.Flights.AddFlightPanel;

import com.App;
import com.assets.components.AutoCompleteComboBoxListener;
import com.assets.components.DateTimePicker;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.Requests;
import com.scenes.GeneralScenes.ModalWindow.ModalWindow;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddFlightController {
    @FXML
    private DateTimePicker dateTimePicker; // FIXME: 23.05.2022
    @FXML
    private ComboBox<String> numbersPlaneComboBox, routesComboBox;

    public void submit() {
        try {
            LocalDateTime dateTime = dateTimePicker.getDateTimeValue();

            String numberPlane = numbersPlaneComboBox.getValue();
            numberPlane = numberPlane == null ? "" : numberPlane;

            String route = routesComboBox.getValue();
            route = route == null ? "" : route;

            //Validation data
            String error = "";
            if (!AddFlightPanel.airplaneNumbersId.containsKey(numberPlane))
                error += "Invalid airplane number selected\n";
            if (!AddFlightPanel.routesId.containsKey(route))
                error += "Invalid route selected\n";
            if (!error.equals("")) {
                ModalWindow.show("Error", error, ModalWindow.Icon.error);
                return;
            }

            //API Request
            int idPlane = AddFlightPanel.airplaneNumbersId.get(numberPlane);
            int idRoute = AddFlightPanel.routesId.get(route);
            String dateTimeStr = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            Requests.addFlight(idPlane, idRoute, dateTimeStr, App.getAccessToken());

            //Reset date, field and combobox
            dateTimePicker.setValue(null);
            numbersPlaneComboBox.setValue("");
            routesComboBox.setValue("");

            ModalWindow.show("Success", "Flight has added", ModalWindow.Icon.success);
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage() + "\nFlight has not added", ModalWindow.Icon.error);
        }
    }

    public void initialize() {
        dateTimePicker.setDayCellFactory(cell -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                if (date.isBefore(LocalDate.now()) || date.isAfter(LocalDate.now().plusYears(1))) {
                    setStyle("-fx-background-color: #801919; -fx-text-fill: white;");
                    setDisable(true);
                }
            }
        });
        new AutoCompleteComboBoxListener<>(numbersPlaneComboBox);
        new AutoCompleteComboBoxListener<>(routesComboBox);
    }
}
