package com.scenes.CustomerPanel.BookingPanel;

import com.App;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.Helpers.PriceByFlight;
import com.assets.services.InteractingWithWindow;
import com.assets.services.Requests;
import com.scenes.GeneralScenes.ShowAllFlightsPanel.ShowAllFlightsPanel;
import com.scenes.ModalWindow.ModalWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.Date;

public class BookingPanel {
    public static void showModal(String from, String to, PriceByFlight price, Date date) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(BookingPanel.class.getResource("BookingPanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();



        stage.show();
    }
}
