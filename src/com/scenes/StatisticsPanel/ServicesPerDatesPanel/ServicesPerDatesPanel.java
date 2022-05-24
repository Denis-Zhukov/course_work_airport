package com.scenes.StatisticsPanel.ServicesPerDatesPanel;

import com.assets.services.InteractingWithWindow;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class ServicesPerDatesPanel {
    public static void showModal() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(ServicesPerDatesPanel.class.getResource("ServicesPerDatesPanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        stage.show();
    }
}
