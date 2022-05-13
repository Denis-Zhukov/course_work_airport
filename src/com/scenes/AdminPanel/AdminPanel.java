package com.scenes.AdminPanel;

import com.assets.services.InteractingWithWindow;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class AdminPanel {
    public static void openChangingScene(Stage stage) {
        //Open needed scene
        FXMLLoader loader = new FXMLLoader(AdminPanel.class.getResource("AdminPanel.fxml"));
        InteractingWithWindow.changeScene(stage, loader);
        stage.centerOnScreen();
    }
}
