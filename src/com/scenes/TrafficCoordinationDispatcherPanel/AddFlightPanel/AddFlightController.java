package com.scenes.TrafficCoordinationDispatcherPanel.AddFlightPanel;

import com.App;
import com.assets.components.AutoCompleteComboBoxListener;
import com.assets.components.DateTimePicker;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import com.scenes.TrafficCoordinationDispatcherPanel.AddAirportPanel.AddAirportPanel;
import com.scenes.TrafficCoordinationDispatcherPanel.AddRoutePanel.AddRoutePanel;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

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

            String error = "";
            if (!AddFlightPanel.airplaneNumbersId.containsKey(numberPlane))
                error += "Invalid airplane number selected\n";
            if (!AddFlightPanel.routesId.containsKey(route))
                error += "Invalid route selected\n";

            if (!error.equals("")) {
                ModalWindow.show("Error", error, ModalWindow.Icon.error);
                return;
            }

            int idPlane = AddFlightPanel.airplaneNumbersId.get(numberPlane);
            int idRoute = AddFlightPanel.routesId.get(route);
            String dateTimeStr = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            Requests.addFlight(idPlane, idRoute, dateTimeStr, App.getAccessToken());

            dateTimePicker.setValue(null);
            numbersPlaneComboBox.setValue("");
            routesComboBox.setValue("");
            ModalWindow.show("Success", "Flight has added", ModalWindow.Icon.success);
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage() + "\nFlight has not added", ModalWindow.Icon.error);
        } catch (DateTimeParseException e) {
            ModalWindow.show("Error", "Incorrect date and time", ModalWindow.Icon.error);
        }
    }

    public void initialize() {
        dateTimePicker.setDayCellFactory(cell -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                if (date.isBefore(LocalDate.now()) || date.isAfter(LocalDate.now().plusYears(1))) {
                    setStyle("-fx-background-color: #ffc0cb; -fx-text-fill: darkgray;");
                    setDisable(true);
                }
            }
        });
        new AutoCompleteComboBoxListener<>(numbersPlaneComboBox);
        new AutoCompleteComboBoxListener<>(routesComboBox);
    }
}
