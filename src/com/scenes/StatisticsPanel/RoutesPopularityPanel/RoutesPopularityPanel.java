package com.scenes.StatisticsPanel.RoutesPopularityPanel;

import com.App;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.InteractingWithWindow;
import com.assets.services.Requests;
import com.scenes.GeneralScenes.ModalWindow.ModalWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RoutesPopularityPanel {
    public static void showModal() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(RoutesPopularityPanel.class.getResource("RoutesPopularityPanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        PieChart chart = ((PieChart) stage.getScene().lookup("#chart"));
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        try {
            Map<String, Integer> popularity = Requests.getRoutesPopularity(App.getAccessToken());
            for (String key : popularity.keySet())
                pieChartData.add(new PieChart.Data(key, popularity.get(key)));
            chart.getData().setAll(pieChartData);

            final Label caption = ((Label) stage.getScene().lookup("#caption"));
            caption.setTextFill(Color.LIGHTSKYBLUE);
            caption.setStyle("-fx-font: 24 arial;");

            for (final PieChart.Data data : chart.getData()) {
                data.getNode().addEventHandler(MouseEvent.MOUSE_MOVED,
                        e -> {
                            caption.setTranslateX(e.getSceneX());
                            caption.setTranslateY(e.getSceneY());
                            caption.setText(Integer.toString((int) data.getPieValue()));
                        });
            }
            for (final PieChart.Data data : chart.getData()) {
                data.getNode().addEventHandler(MouseEvent.MOUSE_EXITED,
                        e -> caption.setText(""));
            }

        } catch (ResponseException | NoServerResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage(), ModalWindow.Icon.error);
            ((Stage) stage.getScene().getWindow()).close();
            return;
        }

        stage.show();
    }
}
