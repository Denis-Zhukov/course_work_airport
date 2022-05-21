package com.scenes.MaintenanceDispatcherPanel;

import com.assets.services.InteractingWithWindow;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class MaintenanceDispatcherPanel {
    public static void openChangingScene(Stage stage) {
        //Open needed scene
        FXMLLoader loader = new FXMLLoader(MaintenanceDispatcherPanel.class.getResource("MaintenanceDispatcherPanel.fxml"));
        InteractingWithWindow.changeScene(stage, loader);
        stage.centerOnScreen();
    }
}
