package com.scenes.MaintenanceDispatcherPanel.AddSeatLayoutTemplatePanel;

import com.assets.services.InteractingWithWindow;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class AddSeatLayoutTemplatePanel {
    public static void showModal(){
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(AddSeatLayoutTemplatePanel.class.getResource("AddSeatLayoutTemplatePanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        stage.show();
    }
}
