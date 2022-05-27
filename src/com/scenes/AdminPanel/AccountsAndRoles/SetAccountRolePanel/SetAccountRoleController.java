package com.scenes.AdminPanel.AccountsAndRoles.SetAccountRolePanel;

import com.App;
import com.assets.components.AutoCompleteComboBoxListener;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
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
        login = login == null ? "" : login;

        String role = rolesComboBox.getValue();
        role = role == null ? "" : role;

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

            //Reset and update combobox
            accountsComboBox.setValue("");
            rolesComboBox.setValue("");

            ModalWindow.show("Success", "Role has set", ModalWindow.Icon.success);
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage() + "\nRole has not set", ModalWindow.Icon.error);
        }
    }

    public void initialize() {
        new AutoCompleteComboBoxListener<>(accountsComboBox);
        new AutoCompleteComboBoxListener<>(rolesComboBox);
    }
}
