package com.scenes.LoginPanel;

import com.assets.services.InteractingWithWindow;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class LoginPanel {
    public static void openChangingScene(Stage stage) {
        //Open needed scene
        FXMLLoader loader = new FXMLLoader(LoginPanel.class.getResource("LoginPanel.fxml"));
        InteractingWithWindow.changeScene(stage, loader);
        stage.centerOnScreen();
    }
}
