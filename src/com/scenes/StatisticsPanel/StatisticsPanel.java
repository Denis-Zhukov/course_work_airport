package com.scenes.StatisticsPanel;

import com.assets.services.InteractingWithWindow;
import com.scenes.EconomistPanel.EconomistPanel;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class StatisticsPanel {
    public static void openChangingScene(Stage stage) {
        FXMLLoader loader = new FXMLLoader(StatisticsPanel.class.getResource("StatisticsPanel.fxml"));
        InteractingWithWindow.changeScene(stage, loader);
        stage.centerOnScreen();
    }
}
