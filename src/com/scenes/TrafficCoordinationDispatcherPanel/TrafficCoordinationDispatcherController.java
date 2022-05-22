package com.scenes.TrafficCoordinationDispatcherPanel;

import com.App;
import com.assets.services.InteractingWithWindow;
import com.scenes.TrafficCoordinationDispatcherPanel.AddCountryPanel.AddCountryPanel;
import com.scenes.TrafficCoordinationDispatcherPanel.ShowAllAirportsPanel.ShowAllAirportsPanel;
import com.scenes.TrafficCoordinationDispatcherPanel.ShowAllFlightsPanel.ShowAllFlightsPanel;
import com.scenes.TrafficCoordinationDispatcherPanel.ShowAllRoutesPanel.ShowAllRoutesController;
import com.scenes.TrafficCoordinationDispatcherPanel.ShowAllRoutesPanel.ShowAllRoutesPanel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TrafficCoordinationDispatcherController {
    @FXML
    private Text loginText, roleText;

    public void logOut() {
        App.resetAccount();
        Stage stage = (Stage) loginText.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(App.class.getResource("App.fxml"));
        InteractingWithWindow.changeScene(stage, loader);
        stage.centerOnScreen();
    }

    @FXML
    void openAddAirportWindow() {

    }

    @FXML
    void openAddCityWindow() {

    }

    @FXML
    void openAddCountryWindow() {
        AddCountryPanel.showModal();
    }

    @FXML
    void openAddFlightWindow() {

    }

    @FXML
    void openAddRouteWindow() {

    }

    @FXML
    void openDeleteAirportWindow() {

    }

    @FXML
    void openDeleteCityWindow() {

    }

    @FXML
    void openDeleteCountryWindow() {

    }

    @FXML
    void openDeleteFlightWindow() {

    }

    @FXML
    void openDeleteRouteWindow() {

    }

    @FXML
    void openEditAirportWindow() {

    }

    @FXML
    void openEditCityWindow() {

    }

    @FXML
    void openEditCountryWindow() {

    }

    @FXML
    void openEditFlightWindow() {

    }

    @FXML
    void openEditRouteWindow() {

    }

    @FXML
    void openShowAllAirportsWindow() {
        ShowAllAirportsPanel.showModal();
    }

    @FXML
    void openShowAllFlightsWindow() {
        ShowAllFlightsPanel.showModal();
    }

    @FXML
    void openShowAllRoutesWindow() {
        ShowAllRoutesPanel.showModal();
    }

    public void initialize() {
        loginText.setText(App.getUsername());
        roleText.setText(App.getRole());
    }
}
