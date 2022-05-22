module client {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires java.net.http;

    opens com to javafx.fxml;
    exports com;

    opens com.assets.components to javafx.fxml;
    exports com.assets.components;

    opens com.scenes.LoginPanel to javafx.fxml;
    exports com.scenes.LoginPanel;

    opens com.scenes.AdminPanel to javafx.fxml;
    exports com.scenes.AdminPanel;

    opens com.scenes.AdminPanel.CreateAccountPanel to javafx.fxml;
    exports com.scenes.AdminPanel.CreateAccountPanel;

    opens com.scenes.AdminPanel.AddRolePanel to javafx.fxml;
    exports com.scenes.AdminPanel.AddRolePanel;

    opens com.scenes.AdminPanel.SetAccountRolePanel to javafx.fxml;
    exports com.scenes.AdminPanel.SetAccountRolePanel;

    opens com.scenes.AdminPanel.DeleteAccountPanel to javafx.fxml;
    exports com.scenes.AdminPanel.DeleteAccountPanel;

    opens com.scenes.AdminPanel.DepriveRolePanel to javafx.fxml;
    exports com.scenes.AdminPanel.DepriveRolePanel;

    opens com.scenes.AdminPanel.DeleteRolePanel to javafx.fxml;
    exports com.scenes.AdminPanel.DeleteRolePanel;

    opens com.scenes.AdminPanel.ChangeAccountPanel to javafx.fxml;
    exports com.scenes.AdminPanel.ChangeAccountPanel;

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

    opens com.scenes.CustomerPanel to javafx.fxml;
    exports com.scenes.CustomerPanel;

    opens com.scenes.ModalWindow to javafx.fxml;
    exports com.scenes.ModalWindow;

    opens com.assets.services to javafx.fxml;
    exports com.assets.services;
    exports com.assets.services.Exceptions;
    opens com.assets.services.Exceptions to javafx.fxml;
}