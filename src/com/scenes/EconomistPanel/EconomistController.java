package com.scenes.EconomistPanel;

import com.App;
import com.assets.services.InteractingWithWindow;
import com.scenes.EconomistPanel.SetFlightPricePanel.SetFlightPricePanel;
import com.scenes.GeneralScenes.ShowAllFlightsPanel.ShowAllFlightsPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EconomistController {
    @FXML
    private Text loginText, roleText;

    public void logOut() {
        App.resetAccount();
        Stage stage = (Stage) loginText.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(App.class.getResource("App.fxml"));
        InteractingWithWindow.changeScene(stage, loader);
        stage.centerOnScreen();
    }

    public void openShowAllFlightsWindow() {
        ShowAllFlightsPanel.showModal();
    }

    public void openSetFlightPriceWindow() {
        SetFlightPricePanel.showModal();
    }

    public void initialize() {
        loginText.setText(App.getUsername());
        roleText.setText(App.getRole());
    }
}
