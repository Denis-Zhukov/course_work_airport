package com.scenes.StatisticsPanel.RoutesPopularityPanel;

import com.App;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.InteractingWithWindow;
import com.assets.services.Requests;
import com.scenes.MaintenanceDispatcherPanel.AddAirplanePanel.AddAirplanePanel;
import com.scenes.ModalWindow.ModalWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoutesPopularityPanel {
    public static void showModal() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(RoutesPopularityPanel.class.getResource("RoutesPopularityPanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        BarChart chart = ((BarChart) stage.getScene().lookup("#chart"));
        List<XYChart.Series<String, Number>> series = new ArrayList<>();

        try {
            Map<String, Integer> popularity = Requests.getRoutesPopularity(App.getAccessToken());
            int i = 0;
            for (String key : popularity.keySet()) {
                series.add(new XYChart.Series<>());
                series.get(i).getData().add(new XYChart.Data(key, popularity.get(key)));
                i++;
            }

            chart.getData().addAll(series);

        } catch (ResponseException | NoServerResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage(), ModalWindow.Icon.error);
            ((Stage) stage.getScene().getWindow()).close();
            return;
        }

        stage.show();
    }
}
