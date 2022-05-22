package com.scenes.TrafficCoordinationDispatcherPanel;

import com.App;
import com.assets.services.InteractingWithWindow;
import com.scenes.TrafficCoordinationDispatcherPanel.AddAirportPanel.AddAirportPanel;
import com.scenes.TrafficCoordinationDispatcherPanel.AddCityPanel.AddCityPanel;
import com.scenes.TrafficCoordinationDispatcherPanel.AddCountryPanel.AddCountryPanel;
import com.scenes.TrafficCoordinationDispatcherPanel.AddRoutePanel.AddRoutePanel;
import com.scenes.TrafficCoordinationDispatcherPanel.DeleteAirportPanel.DeleteAirportPanel;
import com.scenes.TrafficCoordinationDispatcherPanel.DeleteCityPanel.DeleteCityPanel;
import com.scenes.TrafficCoordinationDispatcherPanel.DeleteCountryPanel.DeleteCountryPanel;
import com.scenes.TrafficCoordinationDispatcherPanel.EditAirportPanel.EditAirportPanel;
import com.scenes.TrafficCoordinationDispatcherPanel.EditCityPanel.EditCityPanel;
import com.scenes.TrafficCoordinationDispatcherPanel.EditCountryPanel.EditCountryPanel;
import com.scenes.TrafficCoordinationDispatcherPanel.ShowAllAirportsPanel.ShowAllAirportsPanel;
import com.scenes.TrafficCoordinationDispatcherPanel.ShowAllFlightsPanel.ShowAllFlightsPanel;
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
        AddAirportPanel.showModal();
    }

    @FXML
    void openAddCityWindow() {
        AddCityPanel.showModal();
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
        AddRoutePanel.showModal();
    }

    @FXML
    void openDeleteAirportWindow() {
        DeleteAirportPanel.showModal();
    }

    @FXML
    void openDeleteCityWindow() {
        DeleteCityPanel.showModal();
    }

    @FXML
    void openDeleteCountryWindow() {
        DeleteCountryPanel.showModal();
    }

    @FXML
    void openDeleteFlightWindow() {

    }

    @FXML
    void openDeleteRouteWindow() {

    }

    @FXML
    void openEditAirportWindow() {
        EditAirportPanel.showModal();
    }

    @FXML
    void openEditCityWindow() {
        EditCityPanel.showModal();
    }

    @FXML
    void openEditCountryWindow() {
        EditCountryPanel.showModal();
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
