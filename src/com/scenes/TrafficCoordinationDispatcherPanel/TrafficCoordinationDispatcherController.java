package com.scenes.TrafficCoordinationDispatcherPanel;

import com.App;
import com.assets.services.InteractingWithWindow;
import com.scenes.GeneralScenes.ShowAllFlightsPanel.ShowAllFlightsPanel;
import com.scenes.TrafficCoordinationDispatcherPanel.Airports.AddAirportPanel.AddAirportPanel;
import com.scenes.TrafficCoordinationDispatcherPanel.Cities.AddCityPanel.AddCityPanel;
import com.scenes.TrafficCoordinationDispatcherPanel.Countries.AddCountryPanel.AddCountryPanel;
import com.scenes.TrafficCoordinationDispatcherPanel.Flights.AddFlightPanel.AddFlightPanel;
import com.scenes.TrafficCoordinationDispatcherPanel.Routes.AddRoutePanel.AddRoutePanel;
import com.scenes.TrafficCoordinationDispatcherPanel.Airports.DeleteAirportPanel.DeleteAirportPanel;
import com.scenes.TrafficCoordinationDispatcherPanel.Cities.DeleteCityPanel.DeleteCityPanel;
import com.scenes.TrafficCoordinationDispatcherPanel.Countries.DeleteCountryPanel.DeleteCountryPanel;
import com.scenes.TrafficCoordinationDispatcherPanel.Flights.DeleteFlightPanel.DeleteFlightPanel;
import com.scenes.TrafficCoordinationDispatcherPanel.Routes.DeleteRoutePanel.DeleteRoutePanel;
import com.scenes.TrafficCoordinationDispatcherPanel.Airports.EditAirportPanel.EditAirportPanel;
import com.scenes.TrafficCoordinationDispatcherPanel.Cities.EditCityPanel.EditCityPanel;
import com.scenes.TrafficCoordinationDispatcherPanel.Countries.EditCountryPanel.EditCountryPanel;
import com.scenes.TrafficCoordinationDispatcherPanel.Flights.EditFlightPanel.EditFlightPanel;
import com.scenes.TrafficCoordinationDispatcherPanel.Routes.EditRoutePanel.EditRoutePanel;
import com.scenes.TrafficCoordinationDispatcherPanel.Airports.ShowAllAirportsPanel.ShowAllAirportsPanel;
import com.scenes.TrafficCoordinationDispatcherPanel.Routes.ShowAllRoutesPanel.ShowAllRoutesPanel;
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

    //Airports
    public void openAddAirportWindow() {
        AddAirportPanel.showModal();
    }
    public void openEditAirportWindow() {
        EditAirportPanel.showModal();
    }
    public void openDeleteAirportWindow() {
        DeleteAirportPanel.showModal();
    }
    public void openShowAllAirportsWindow() {
        ShowAllAirportsPanel.showModal();
    }

    //Cities
    public void openAddCityWindow() {
        AddCityPanel.showModal();
    }
    public void openEditCityWindow() {
        EditCityPanel.showModal();
    }
    public void openDeleteCityWindow() {
        DeleteCityPanel.showModal();
    }

    //Countries
    public void openAddCountryWindow() {
        AddCountryPanel.showModal();
    }
    public void openEditCountryWindow() {
        EditCountryPanel.showModal();
    }
    public void openDeleteCountryWindow() {
        DeleteCountryPanel.showModal();
    }

    //Flights
    public void openAddFlightWindow() {
        AddFlightPanel.showModal();
    }
    public void openEditFlightWindow() {
        EditFlightPanel.showModal();
    }
    public void openDeleteFlightWindow() {
        DeleteFlightPanel.showModal();
    }
    public void openShowAllFlightsWindow() {
        ShowAllFlightsPanel.showModal();
    }

    //Routes
    public void openAddRouteWindow() {
        AddRoutePanel.showModal();
    }
    public void openEditRouteWindow() {
        EditRoutePanel.showModal();
    }
    public void openDeleteRouteWindow() {
        DeleteRoutePanel.showModal();
    }
    public void openShowAllRoutesWindow() {
        ShowAllRoutesPanel.showModal();
    }

    public void initialize() {
        loginText.setText(App.getUsername());
        roleText.setText(App.getRole());
    }
}
