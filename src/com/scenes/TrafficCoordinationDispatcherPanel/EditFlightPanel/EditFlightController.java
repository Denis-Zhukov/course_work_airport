package com.scenes.TrafficCoordinationDispatcherPanel.EditFlightPanel;

import com.App;
import com.assets.components.AutoCompleteComboBoxListener;
import com.assets.components.DateTimePicker;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EditFlightController {
    @FXML
    private DateTimePicker dateTimePicker; // FIXME: 23.05.2022
    @FXML
    private ComboBox<String> flightsComboBox, routesComboBox, planesComboBox;

    public void submit() {
        try {
            LocalDateTime dateTime = dateTimePicker.getDateTimeValue();

            String flight = flightsComboBox.getValue();
            flight = flight == null ? "" : flight;

            String numberPlane = planesComboBox.getValue();
            numberPlane = numberPlane == null ? "" : numberPlane;

            String route = routesComboBox.getValue();
            route = route == null ? "" : route;

            String error = "";
            if (!EditFlightPanel.flightsId.containsKey(flight))
                error += "Invalid flight selected\n";
            if (!EditFlightPanel.airplaneNumbersId.containsKey(numberPlane))
                error += "Invalid airplane number selected\n";
            if (!EditFlightPanel.routesId.containsKey(route))
                error += "Invalid route selected\n";

            if (!error.equals("")) {
                ModalWindow.show("Error", error, ModalWindow.Icon.error);
                return;
            }

            int idFlight = EditFlightPanel.flightsId.get(flight);
            int idPlane = EditFlightPanel.airplaneNumbersId.get(numberPlane);
            int idRoute = EditFlightPanel.routesId.get(route);
            String dateTimeStr = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            Requests.updateFlight(idFlight, idRoute,idPlane, dateTimeStr, App.getAccessToken());

            ((Stage) dateTimePicker.getScene().getWindow()).close();
            ModalWindow.show("Success", "Flight has edited", ModalWindow.Icon.success);
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage() + "\nFlight has not edited", ModalWindow.Icon.error);
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
        new AutoCompleteComboBoxListener<>(flightsComboBox);
        new AutoCompleteComboBoxListener<>(routesComboBox);
        new AutoCompleteComboBoxListener<>(planesComboBox);
    }
}
