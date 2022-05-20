package com.scenes.MaintenanceDispatcherPanel.DeleteAirplanePanel;

import com.assets.services.InteractingWithWindow;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;


public class DeleteAirplanePanel {
    public static void showModal() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(DeleteAirplanePanel.class.getResource("DeleteAirplanePanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        //ЗАГРУЗКА НОМЕРОВ И ID
        stage.getScene().lookup("#numberAirplainComboBox");

        stage.show();
    }
}
