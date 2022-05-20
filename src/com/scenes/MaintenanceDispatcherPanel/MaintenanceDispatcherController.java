package com.scenes.MaintenanceDispatcherPanel;

import com.App;
import com.assets.services.InteractingWithWindow;
import com.scenes.MaintenanceDispatcherPanel.AddAirplanePanel.AddAirplanePanel;
import com.scenes.MaintenanceDispatcherPanel.DeleteAirplanePanel.DeleteAirplanePanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MaintenanceDispatcherController {
    @FXML
    private Text loginText;
    @FXML
    private Text roleText;

    public void logOut() {
        App.resetAccount();
        Stage stage = (Stage) loginText.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(App.class.getResource("App.fxml"));
        InteractingWithWindow.changeScene(stage, loader);
        stage.centerOnScreen();
    }

    public void initialize() {
        loginText.setText(App.getUsername());
        roleText.setText(App.getRole());
    }

    public void openAddAirplaneWindow() {
        AddAirplanePanel.showModal();
    }

    public void openDeleteAirplaneWindow() {
        DeleteAirplanePanel.showModal();
    }
}
