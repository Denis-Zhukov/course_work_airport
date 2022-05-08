package com;

import com.scenes.CustomerPanel.CustomerPanel;
import com.scenes.LoginPanel.LoginPanel;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AppController {
    @FXML
    ImageView loginPanel;

    public void openLoginPanel() {
        LoginPanel.openChangingScene((Stage) loginPanel.getScene().getWindow());
    }

    public void openCustomerPanel() {
        CustomerPanel.openChangingScene((Stage) loginPanel.getScene().getWindow());
    }
}
