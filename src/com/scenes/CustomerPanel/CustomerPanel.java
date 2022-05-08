package com.scenes.CustomerPanel;

import com.assets.services.InteractingWithWindow;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class CustomerPanel {
    public static void openChangingScene(Stage stage) {
        FXMLLoader loader = new FXMLLoader(CustomerPanel.class.getResource("CustomerPanel.fxml"));
        InteractingWithWindow.changeScene(stage, loader);
        stage.centerOnScreen();
    }
}
