package com.scenes.StatisticsPanel;

import com.App;
import com.assets.services.InteractingWithWindow;
import com.scenes.StatisticsPanel.RoutesPopularityPanel.RoutesPopularityPanel;
import com.scenes.StatisticsPanel.ServicesPerDatesPanel.ServicesPerDatesPanel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StatisticsController {
    @FXML
    private Text loginText, roleText;

    public void logOut() {
        App.resetAccount();
        Stage stage = (Stage) loginText.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(App.class.getResource("App.fxml"));
        InteractingWithWindow.changeScene(stage, loader);
        stage.centerOnScreen();
    }

    public void openRoutesPopularityWindow() {
        RoutesPopularityPanel.showModal();
    }

    public void openServicesProvedPerDatesWindow() {
        ServicesPerDatesPanel.showModal();
    }

    public void initialize() {
        loginText.setText(App.getUsername());
        roleText.setText(App.getRole());
    }
}
