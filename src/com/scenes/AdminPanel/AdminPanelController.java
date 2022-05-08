package com.scenes.AdminPanel;

import com.App;
import com.assets.services.InteractingWithWindow;
import com.scenes.AdminPanel.AddRolePanel.AddRolePanel;
import com.scenes.AdminPanel.CreateAccountPanel.CreateAccountPanel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminPanelController {
    @FXML
    Button logOutBtn;

    public void logOut() {
        App.resetAccount();
        Stage stage = (Stage) logOutBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(App.class.getResource("App.fxml"));
        InteractingWithWindow.changeScene(stage, loader);
        stage.centerOnScreen();
    }

    public void openCreateAccountWindow() {
        CreateAccountPanel.showModal();
    }

    public void openAddingRoleWindow() {
        AddRolePanel.showModal();
    }
}
