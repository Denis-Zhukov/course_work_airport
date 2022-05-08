package com.scenes.CustomerPanel;

import com.App;
import com.assets.services.InteractingWithWindow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class CustomerController {
    @FXML
    Button backBtn;

    public void backToEnter() {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(App.class.getResource("App.fxml"));
        InteractingWithWindow.changeScene(stage, loader);
        stage.centerOnScreen();
    }
}
