package com.scenes.EconomistPanel.SetFlightPricePanel;

import com.App;
import com.assets.components.AutoCompleteComboBoxListener;
import com.assets.components.NumberField;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.Helpers.PriceByFlight;
import com.assets.services.Requests;
import com.scenes.GeneralScenes.ModalWindow.ModalWindow;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.math.BigDecimal;

public class SetFlightPriceController {
    @FXML
    private NumberField firstClassPriceTextField, businessClassPriceTextField, economyClassPriceTextField;
    @FXML
    private ComboBox<String> flightsComboBox;

    public void preloadPrices() {
        String flight = flightsComboBox.getValue();
        flight = flight == null ? "" : flight.trim();

        if (!SetFlightPricePanel.flightsId.containsKey(flight))
            return;

        int id = SetFlightPricePanel.flightsId.get(flight);
        try {
            PriceByFlight prices = Requests.getPrices(id);
            if (prices == null) return;
            var l = prices.getFirstPrice();
            firstClassPriceTextField.setText(prices.getFirstPrice());
            businessClassPriceTextField.setText(prices.getBusinessPrice());
            economyClassPriceTextField.setText(prices.getEconomyPrice());
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage() + "\nData loading error", ModalWindow.Icon.error);
        }
    }

    public void submit() {
        try {
            BigDecimal firstClassPrice = BigDecimal.valueOf(firstClassPriceTextField.getDoubleValue());
            BigDecimal businessClassPrice = BigDecimal.valueOf(businessClassPriceTextField.getDoubleValue());
            BigDecimal economyClassPrice = BigDecimal.valueOf(economyClassPriceTextField.getDoubleValue());

            String flight = flightsComboBox.getValue();
            flight = flight == null ? "" : flight;

            String error = "";
            if (!SetFlightPricePanel.flightsId.containsKey(flight))
                error += "Invalid flight selected\n";

            if (!error.equals("")) {
                ModalWindow.show("Error", error, ModalWindow.Icon.error);
                return;
            }

            int idFlight = SetFlightPricePanel.flightsId.get(flight);
            Requests.setPrice(idFlight, firstClassPrice, businessClassPrice, economyClassPrice, App.getAccessToken());
            firstClassPriceTextField.setText("");
            businessClassPriceTextField.setText("");
            economyClassPriceTextField.setText("");
            flightsComboBox.setValue("");
            ModalWindow.show("Success", "Prices for the flight have set", ModalWindow.Icon.success);
        } catch (NumberFormatException e) {
            ModalWindow.show("Error", "Invalid prices. Prices for the flight have not set", ModalWindow.Icon.error);
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage() + "\nPrices for the flight have not set", ModalWindow.Icon.error);
        }
    }

    public void initialize() {
        new AutoCompleteComboBoxListener<>(flightsComboBox);
    }
}
