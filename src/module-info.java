module client {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires java.net.http;
    requires java.sql;

    opens com to javafx.fxml;
    exports com;

    opens com.assets.components to javafx.fxml;
    exports com.assets.components;

    opens com.scenes.LoginPanel to javafx.fxml;
    exports com.scenes.LoginPanel;

    opens com.scenes.AdminPanel to javafx.fxml;
    exports com.scenes.AdminPanel;

    opens com.scenes.AdminPanel.Accounts.CreateAccountPanel to javafx.fxml;
    exports com.scenes.AdminPanel.Accounts.CreateAccountPanel;

    opens com.scenes.AdminPanel.Roles.AddRolePanel to javafx.fxml;
    exports com.scenes.AdminPanel.Roles.AddRolePanel;

    opens com.scenes.AdminPanel.AccountsAndRoles.SetAccountRolePanel to javafx.fxml;
    exports com.scenes.AdminPanel.AccountsAndRoles.SetAccountRolePanel;

    opens com.scenes.AdminPanel.Accounts.DeleteAccountPanel to javafx.fxml;
    exports com.scenes.AdminPanel.Accounts.DeleteAccountPanel;

    opens com.scenes.AdminPanel.AccountsAndRoles.DepriveRolePanel to javafx.fxml;
    exports com.scenes.AdminPanel.AccountsAndRoles.DepriveRolePanel;

    opens com.scenes.AdminPanel.Roles.DeleteRolePanel to javafx.fxml;
    exports com.scenes.AdminPanel.Roles.DeleteRolePanel;

    opens com.scenes.AdminPanel.Accounts.ChangeAccountPanel to javafx.fxml;
    exports com.scenes.AdminPanel.Accounts.ChangeAccountPanel;

    opens com.scenes.MaintenanceDispatcherPanel to javafx.fxml;
    exports com.scenes.MaintenanceDispatcherPanel;

    opens com.scenes.MaintenanceDispatcherPanel.Airplanes.AddAirplanePanel to javafx.fxml;
    exports com.scenes.MaintenanceDispatcherPanel.Airplanes.AddAirplanePanel;

    opens com.scenes.MaintenanceDispatcherPanel.Airplanes.DeleteAirplanePanel to javafx.fxml;
    exports com.scenes.MaintenanceDispatcherPanel.Airplanes.DeleteAirplanePanel;

    opens com.scenes.MaintenanceDispatcherPanel.Airplanes.EditAirplanePanel to javafx.fxml;
    exports com.scenes.MaintenanceDispatcherPanel.Airplanes.EditAirplanePanel;

    opens com.scenes.MaintenanceDispatcherPanel.SeatLayoutTemplates.AddSeatLayoutTemplatePanel to javafx.fxml;
    exports com.scenes.MaintenanceDispatcherPanel.SeatLayoutTemplates.AddSeatLayoutTemplatePanel;

    opens com.scenes.MaintenanceDispatcherPanel.SeatLayoutTemplates.DeleteSeatLayoutTemplatePanel to javafx.fxml;
    exports com.scenes.MaintenanceDispatcherPanel.SeatLayoutTemplates.DeleteSeatLayoutTemplatePanel;

    opens com.scenes.MaintenanceDispatcherPanel.SeatLayoutTemplates.ShowSeatingLayoutTemplatesPanel to javafx.fxml;
    exports com.scenes.MaintenanceDispatcherPanel.SeatLayoutTemplates.ShowSeatingLayoutTemplatesPanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel.Airports.ShowAllAirportsPanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel.Airports.ShowAllAirportsPanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel.Routes.ShowAllRoutesPanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel.Routes.ShowAllRoutesPanel;

    opens com.scenes.GeneralScenes.ShowAllFlightsPanel to javafx.fxml;
    exports com.scenes.GeneralScenes.ShowAllFlightsPanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel.Countries.AddCountryPanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel.Countries.AddCountryPanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel.Countries.EditCountryPanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel.Countries.EditCountryPanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel.Countries.DeleteCountryPanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel.Countries.DeleteCountryPanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel.Cities.AddCityPanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel.Cities.AddCityPanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel.Cities.EditCityPanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel.Cities.EditCityPanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel.Cities.DeleteCityPanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel.Cities.DeleteCityPanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel.Airports.AddAirportPanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel.Airports.AddAirportPanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel.Airports.EditAirportPanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel.Airports.EditAirportPanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel.Airports.DeleteAirportPanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel.Airports.DeleteAirportPanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel.Routes.AddRoutePanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel.Routes.AddRoutePanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel.Routes.EditRoutePanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel.Routes.EditRoutePanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel.Routes.DeleteRoutePanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel.Routes.DeleteRoutePanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel.Flights.AddFlightPanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel.Flights.AddFlightPanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel.Flights.EditFlightPanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel.Flights.EditFlightPanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel.Flights.DeleteFlightPanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel.Flights.DeleteFlightPanel;

    opens com.scenes.EconomistPanel to javafx.fxml;
    exports com.scenes.EconomistPanel;

    opens com.scenes.EconomistPanel.SetFlightPricePanel to javafx.fxml;
    exports com.scenes.EconomistPanel.SetFlightPricePanel;

    opens com.scenes.StatisticsPanel to javafx.fxml;
    exports com.scenes.StatisticsPanel;

    opens com.scenes.StatisticsPanel.RoutesPopularityPanel to javafx.fxml;
    exports com.scenes.StatisticsPanel.RoutesPopularityPanel;

    opens com.scenes.StatisticsPanel.ServicesPerDatesPanel to javafx.fxml;
    exports com.scenes.StatisticsPanel.ServicesPerDatesPanel;

    opens com.scenes.CustomerPanel to javafx.fxml;
    exports com.scenes.CustomerPanel;

    opens com.scenes.CustomerPanel.BookingPanel to javafx.fxml;
    exports com.scenes.CustomerPanel.BookingPanel;

    opens com.scenes.GeneralScenes.ModalWindow to javafx.fxml;
    exports com.scenes.GeneralScenes.ModalWindow;

    opens com.assets.services to javafx.fxml;
    exports com.assets.services;
    exports com.assets.services.Exceptions;
    opens com.assets.services.Exceptions to javafx.fxml;
    exports com.assets.services.TableRows;
    opens com.assets.services.TableRows to javafx.fxml;
    exports com.assets.services.Helpers;
    opens com.assets.services.Helpers to javafx.fxml;
}