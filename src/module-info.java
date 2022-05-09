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

    opens com.scenes.CustomerPanel to javafx.fxml;
    exports com.scenes.CustomerPanel;

    opens com.scenes.ModalWindow to javafx.fxml;
    exports com.scenes.ModalWindow;

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
}