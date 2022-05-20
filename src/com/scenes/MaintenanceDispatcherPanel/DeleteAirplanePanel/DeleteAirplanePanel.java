package com.scenes.MaintenanceDispatcherPanel.DeleteAirplanePanel;

import com.assets.services.InteractingWithWindow;
import com.scenes.MaintenanceDispatcherPanel.AddAirplanePanel.AddAirplanePanel;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;


public class DeleteAirplanePanel {
    public static void showModal() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(AddAirplanePanel.class.getResource("AddAirplanePanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        stage.show();
    }
}
