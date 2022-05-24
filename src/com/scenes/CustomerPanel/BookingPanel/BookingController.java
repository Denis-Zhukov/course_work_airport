package com.scenes.CustomerPanel.BookingPanel;

import com.assets.services.Constants;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static com.scenes.CustomerPanel.BookingPanel.BookingPanel.idFlight;
import static com.scenes.CustomerPanel.BookingPanel.BookingPanel.price;

public class BookingController {
    @FXML
    private TextField fullNameTextField, passportTextField;
    @FXML
    private TextField costTextField;
    @FXML
    private ComboBox<String> classesComboBox;

    public void submit() {
        String fullName = fullNameTextField.getText();
        String passport = passportTextField.getText();
        String class_ = classesComboBox.getValue();
        class_ = class_ == null ? "" : class_;

        String error = "";
        if (!Constants.regexFullName.matcher(fullName).find())
            error += "Invalid full name\n";
        if (!Constants.regexPassport.matcher(passport).find())
            error += "Invalid passport\n";
        if (!classesComboBox.getItems().contains(class_))
            error += "Invalid class\n";

        if (!error.equals("")) {
            ModalWindow.show("Error", error, ModalWindow.Icon.error);
            return;
        }

        int idClass = 0;
        switch (classesComboBox.getValue()) {
            case "First class" -> idClass = 1;
            case "Business class" -> idClass = 2;
            case "Economy class" -> idClass = 3;
        }

        try {
            Requests.addTicket(idFlight, idClass, fullName, passport);
            ((Stage)classesComboBox.getScene().getWindow()).close();
            ModalWindow.show("Success", "Ticket has booked", ModalWindow.Icon.success);
        } catch (ResponseException | NoServerResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage() + "\nTicket has not booked", ModalWindow.Icon.error);
        }
    }

    public void preloadPrice() {
        switch (classesComboBox.getValue()) {
            case "First class" -> costTextField.setText(price.getFirstPrice());
            case "Business class" -> costTextField.setText(price.getBusinessPrice());
            case "Economy class" -> costTextField.setText(price.getEconomyPrice());
            default -> {
            }
        }
    }
}
