package com.scenes.AdminPanel.SetAccountRolePanel;

import com.App;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class SetAccountRoleController {
    @FXML
    ComboBox<String> accountsComboBox;
    @FXML
    ComboBox<String> rolesComboBox;

    public void submit() {
        String login = accountsComboBox.getValue();
        String role = rolesComboBox.getValue();

        //Validation data
        String error = "";
        if (!SetAccountRolePanel.accounts.containsKey(login))
            error += "Invalid account specified\n";
        if (!SetAccountRolePanel.roles.containsKey(role))
            error += "Invalid account specified";

        if (!error.equals("")) {
            ModalWindow.show("Error", error, ModalWindow.Icon.error);
            return;
        }

        //API Request
        try {
            Requests.setRole(SetAccountRolePanel.accounts.get(login), SetAccountRolePanel.roles.get(role), App.getAccessToken());
            ModalWindow.show("Success", "Role set", ModalWindow.Icon.success);
        } catch (Exception e) {
            ModalWindow.show("Error", e.getMessage(), ModalWindow.Icon.error);
        }
    }
}
