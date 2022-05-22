package com.scenes.TrafficCoordinationDispatcherPanel.AddCountryPanel;

import com.assets.services.InteractingWithWindow;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class AddCountryPanel {
    public static void showModal() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(AddCountryPanel.class.getResource("AddCountryPanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        stage.show();
    }
}
