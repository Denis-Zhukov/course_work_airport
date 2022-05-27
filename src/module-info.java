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

    opens com.scenes.MaintenanceDispatcherPanel.AddAirplanePanel to javafx.fxml;
    exports com.scenes.MaintenanceDispatcherPanel.AddAirplanePanel;

    opens com.scenes.MaintenanceDispatcherPanel.DeleteAirplanePanel to javafx.fxml;
    exports com.scenes.MaintenanceDispatcherPanel.DeleteAirplanePanel;

    opens com.scenes.MaintenanceDispatcherPanel.EditAirplanePanel to javafx.fxml;
    exports com.scenes.MaintenanceDispatcherPanel.EditAirplanePanel;

    opens com.scenes.MaintenanceDispatcherPanel.AddSeatLayoutTemplatePanel to javafx.fxml;
    exports com.scenes.MaintenanceDispatcherPanel.AddSeatLayoutTemplatePanel;

    opens com.scenes.MaintenanceDispatcherPanel.DeleteSeatLayoutTemplatePanel to javafx.fxml;
    exports com.scenes.MaintenanceDispatcherPanel.DeleteSeatLayoutTemplatePanel;

    opens com.scenes.MaintenanceDispatcherPanel.ShowSeatingLayoutTemplatesPanel to javafx.fxml;
    exports com.scenes.MaintenanceDispatcherPanel.ShowSeatingLayoutTemplatesPanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel.ShowAllAirportsPanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel.ShowAllAirportsPanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel.ShowAllRoutesPanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel.ShowAllRoutesPanel;

    opens com.scenes.GeneralScenes.ShowAllFlightsPanel to javafx.fxml;
    exports com.scenes.GeneralScenes.ShowAllFlightsPanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel.AddCountryPanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel.AddCountryPanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel.EditCountryPanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel.EditCountryPanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel.DeleteCountryPanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel.DeleteCountryPanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel.AddCityPanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel.AddCityPanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel.EditCityPanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel.EditCityPanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel.DeleteCityPanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel.DeleteCityPanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel.AddAirportPanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel.AddAirportPanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel.EditAirportPanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel.EditAirportPanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel.DeleteAirportPanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel.DeleteAirportPanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel.AddRoutePanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel.AddRoutePanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel.EditRoutePanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel.EditRoutePanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel.DeleteRoutePanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel.DeleteRoutePanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel.AddFlightPanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel.AddFlightPanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel.EditFlightPanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel.EditFlightPanel;

    opens com.scenes.TrafficCoordinationDispatcherPanel.DeleteFlightPanel to javafx.fxml;
    exports com.scenes.TrafficCoordinationDispatcherPanel.DeleteFlightPanel;

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

    opens com.scenes.ModalWindow to javafx.fxml;
    exports com.scenes.ModalWindow;

    opens com.assets.services to javafx.fxml;
    exports com.assets.services;
    exports com.assets.services.Exceptions;
    opens com.assets.services.Exceptions to javafx.fxml;
    exports com.assets.services.TableRows;
    opens com.assets.services.TableRows to javafx.fxml;
    exports com.assets.services.Helpers;
    opens com.assets.services.Helpers to javafx.fxml;
}