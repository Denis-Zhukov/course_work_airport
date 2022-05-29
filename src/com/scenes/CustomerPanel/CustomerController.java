package com.scenes.CustomerPanel;

import com.App;
import com.assets.components.AutoCompleteComboBoxListener;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.Helpers.PriceByFlight;
import com.assets.services.InteractingWithWindow;
import com.assets.services.Requests;
import com.assets.services.TableRows.CustomerRow;
import com.scenes.CustomerPanel.BookingPanel.BookingPanel;
import com.scenes.GeneralScenes.ModalWindow.ModalWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class CustomerController {
    public TableColumn fromColumn, toColumn;
    public TableColumn datetimeColumn;
    public TableColumn firstPriceColumn, businessPriceColumn, economyPriceColumn;
    public TableColumn bookPlaneBtnsColumn;
    public TableView table;
    @FXML
    private ComboBox<String> fromComboBox, toComboBox;
    @FXML
    private DatePicker whenDatePicker;
    @FXML
    private Button backBtn;

    public void backToEnter() {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(App.class.getResource("App.fxml"));
        InteractingWithWindow.changeScene(stage, loader);
        stage.centerOnScreen();
    }

    public void find() {
        LocalDate date = whenDatePicker.getValue();
        date = date == null ? LocalDate.now() : date;

        String from = fromComboBox.getValue();
        from = from == null ? "" : from;

        String to = toComboBox.getValue();
        to = to == null ? "" : to;

        //Validation data
        String error = "";
        if (!CustomerPanel.airportsId.containsKey(from))
            error += "Departure airport is not selected from dropdown list\n";
        if (!CustomerPanel.airportsId.containsKey(to))
            error += "Arrival airport is not selected from dropdown list\n";
        if (to.equals(from))
            error += "Departure and arrival airports are the same\n";
        if (!error.equals("")) {
            ModalWindow.show("Error", error, ModalWindow.Icon.error);
            return;
        }

        //API Request
        try {
            int idFrom = CustomerPanel.airportsId.get(from);
            int idTo = CustomerPanel.airportsId.get(to);
            Integer idRoute = Requests.getRouteByFromTo(idFrom, idTo);
            if (idRoute == null) return;

            Map<Integer, Date> idFlightDate = Requests.getDateAndIdsByRouteAndDate(idRoute, date);
            Set<Integer> ids = idFlightDate.keySet();

            Map<Integer, PriceByFlight> idCost = new HashMap<>();
            for (Integer idFlight : ids)
                idCost.put(idFlight, Requests.getPrices(idFlight));

            ObservableList<CustomerRow> rows = FXCollections.observableArrayList();
            for (Integer idFlight : ids) {
                if (idCost.get(idFlight) == null) continue;
                String finalFrom = from;
                String finalTo = to;
                rows.add(new CustomerRow(
                        from, to,
                        idFlightDate.get(idFlight),
                        idCost.get(idFlight).getFirstPrice(),
                        idCost.get(idFlight).getBusinessPrice(),
                        idCost.get(idFlight).getEconomyPrice(),
                        (e) -> BookingPanel.showModal(finalFrom, finalTo,  idCost.get(idFlight), idFlightDate.get(idFlight), idFlight)
                ));
            }

            table.setItems(rows);
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage(), ModalWindow.Icon.error);
        }
    }

    public void initialize() {
        whenDatePicker.setDayCellFactory(cell -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                if (date.isBefore(LocalDate.now()) || date.isAfter(LocalDate.now().plusYears(2))) {
                    setStyle("-fx-background-color: #801919; -fx-text-fill: white;");
                    setDisable(true);
                }
            }
        });
        new AutoCompleteComboBoxListener<>(fromComboBox);
        new AutoCompleteComboBoxListener<>(toComboBox);


        fromColumn.setCellValueFactory(new PropertyValueFactory<CustomerRow, String>("from"));
        toColumn.setCellValueFactory(new PropertyValueFactory<CustomerRow, String>("to"));
        datetimeColumn.setCellValueFactory(new PropertyValueFactory<CustomerRow, String>("dateTime"));

        firstPriceColumn.setCellValueFactory(new PropertyValueFactory<CustomerRow, String>("firstCost"));
        businessPriceColumn.setCellValueFactory(new PropertyValueFactory<CustomerRow, String>("businessCost"));
        economyPriceColumn.setCellValueFactory(new PropertyValueFactory<CustomerRow, String>("economyCost"));

        bookPlaneBtnsColumn.setCellValueFactory(new PropertyValueFactory<CustomerRow, String>("book"));
    }
}
