package com.scenes.AdminPanel;

import com.App;
import com.assets.services.InteractingWithWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminPanel {
    public static void openChangingScene(Stage stage) {
        FXMLLoader loader = new FXMLLoader(AdminPanel.class.getResource("AdminPanel.fxml"));
        InteractingWithWindow.changeScene(stage, loader);
        ((Text)stage.getScene().lookup("#loginText")).setText(App.getUsername());
        stage.centerOnScreen();
    }
}
