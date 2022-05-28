package com.scenes.MaintenanceDispatcherPanel;

import com.App;
import com.assets.services.InteractingWithWindow;
import com.scenes.MaintenanceDispatcherPanel.Airplanes.AddAirplanePanel.AddAirplanePanel;
import com.scenes.MaintenanceDispatcherPanel.SeatLayoutTemplates.AddSeatLayoutTemplatePanel.AddSeatLayoutTemplatePanel;
import com.scenes.MaintenanceDispatcherPanel.Airplanes.DeleteAirplanePanel.DeleteAirplanePanel;
import com.scenes.MaintenanceDispatcherPanel.SeatLayoutTemplates.DeleteSeatLayoutTemplatePanel.DeleteSeatLayoutTemplatePanel;
import com.scenes.MaintenanceDispatcherPanel.Airplanes.EditAirplanePanel.EditAirplanePanel;
import com.scenes.MaintenanceDispatcherPanel.SeatLayoutTemplates.ShowSeatingLayoutTemplatesPanel.ShowSeatingLayoutTemplatesPanel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MaintenanceDispatcherController {
    @FXML
    private Text loginText, roleText;

    public void logOut() {
        App.resetAccount();
        Stage stage = (Stage) loginText.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(App.class.getResource("App.fxml"));
        InteractingWithWindow.changeScene(stage, loader);
        stage.centerOnScreen();
    }

    //Airplanes
    public void openShowSeatingLayoutTemplatesPanel() {
        ShowSeatingLayoutTemplatesPanel.showModal();
    }
    public void openAddAirplaneWindow() {
        AddAirplanePanel.showModal();
    }
    public void openDeleteSeatLayoutTemplatePanel() {
        DeleteSeatLayoutTemplatePanel.showModal();
    }

    //SeatLayoutTemplates
    public void openAddSeatLayoutTemplateWindow() {
        AddSeatLayoutTemplatePanel.showModal();
    }
    public void openEditAirplaneWindow() {
        EditAirplanePanel.showModal();
    }
    public void openDeleteAirplaneWindow() {
        DeleteAirplanePanel.showModal();
    }

    public void initialize() {
        loginText.setText(App.getUsername());
        roleText.setText(App.getRole());
    }
}
