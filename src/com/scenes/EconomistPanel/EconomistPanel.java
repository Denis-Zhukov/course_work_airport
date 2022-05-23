package com.scenes.EconomistPanel;

import com.assets.services.InteractingWithWindow;
import com.scenes.LoginPanel.LoginPanel;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class EconomistPanel {
    public static void openChangingScene(Stage stage) {
        FXMLLoader loader = new FXMLLoader(EconomistPanel.class.getResource("EconomistPanel.fxml"));
        InteractingWithWindow.changeScene(stage, loader);
        stage.centerOnScreen();
    }
}
