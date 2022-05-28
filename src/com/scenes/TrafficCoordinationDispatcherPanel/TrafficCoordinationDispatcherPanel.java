package com.scenes.TrafficCoordinationDispatcherPanel;

import com.assets.services.InteractingWithWindow;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class TrafficCoordinationDispatcherPanel {
    public static void openChangingScene(Stage stage) {
        //Open needed window
        FXMLLoader loader = new FXMLLoader(TrafficCoordinationDispatcherPanel.class.getResource("TrafficCoordinationDispatcherPanel.fxml"));
        InteractingWithWindow.changeScene(stage, loader);
        stage.centerOnScreen();
    }
}
