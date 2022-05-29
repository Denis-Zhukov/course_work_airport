package com.scenes.CustomerPanel.BookingPanel;

import com.assets.services.Helpers.PriceByFlight;
import com.assets.services.InteractingWithWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BookingPanel {
    static int idFlight;
    static PriceByFlight price;

    public static void showModal(String from, String to, PriceByFlight price, Date date, int idFlight) {
        //Open needed window
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(BookingPanel.class.getResource("BookingPanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        BookingPanel.idFlight = idFlight;
        BookingPanel.price = price;
        ((TextField) stage.getScene().lookup("#fromTextField")).setText(from);
        ((TextField) stage.getScene().lookup("#toTextField")).setText(to);
        ((TextField) stage.getScene().lookup("#dateTextField")).setText(new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(date));

        ComboBox<String> classesCB = ((ComboBox) stage.getScene().lookup("#classesComboBox"));
        if (!price.getFirstPrice().equals("0.00"))
            classesCB.getItems().add("First class");
        if (!price.getBusinessPrice().equals("0.00"))
            classesCB.getItems().add("Business class");
        if (!price.getEconomyPrice().equals("0.00"))
            classesCB.getItems().add("Economy class");

        stage.show();
    }
}
