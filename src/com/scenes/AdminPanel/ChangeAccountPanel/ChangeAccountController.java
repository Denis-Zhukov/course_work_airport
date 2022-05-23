package com.scenes.AdminPanel.ChangeAccountPanel;

import com.App;
import com.assets.components.LengthLimitedPasswordField;
import com.assets.components.LengthLimitedTextField;
import com.assets.services.Constants;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class ChangeAccountController {
    @FXML
    private ComboBox<String> usernameComboBox;
    @FXML
    private LengthLimitedTextField loginField;
    @FXML
    private LengthLimitedPasswordField passwordField;
    @FXML
    private ComboBox<String> newRoleComboBox;

    public void submit() {
        String account = usernameComboBox.getValue();
        account = account == null ? "" : account.trim();

        String newUsername = loginField.getText();
        String newPassword = passwordField.getText();

        String role = newRoleComboBox.getValue();
        role = role == null ? "" : role.trim();

        //Validation data
        String error = "";
        if (!ChangeAccountPanel.accounts.containsKey(account))
            error += "Invalid current account\n";
        if (!Constants.regexLogin.matcher(newUsername).find())
            error += "Invalid new username\n";
        if (!(newPassword.equals("") || Constants.regexPassword.matcher(newPassword).find()))
            error += "Invalid new password\n";
        if (!ChangeAccountPanel.roles.containsKey(role))
            error += "Incorrect new role\n";

        if (!error.equals("")) {
            ModalWindow.show("Error", error, ModalWindow.Icon.error);
            return;
        }

        //API Request
        try {
            Requests.updateAccount(
                    ChangeAccountPanel.accounts.get(account),
                    newUsername,
                    newPassword,
                    ChangeAccountPanel.roles.get(role),
                    App.getAccessToken());

            ModalWindow.show("Success", "Account updated", ModalWindow.Icon.success);
            usernameComboBox.setValue("");
            loginField.setText("");
            passwordField.setText("");
            newRoleComboBox.setValue("");
        } catch (Exception e) {
            ModalWindow.show("Error", e.getMessage(), ModalWindow.Icon.error);
        }
    }

    public void preloadAccount() {
        loginField.setText(usernameComboBox.getValue());
        String username = usernameComboBox.getValue();
        if (!ChangeAccountPanel.accounts.containsKey(username))
            return;
        String role = Requests.getRole(ChangeAccountPanel.accounts.get(username), App.getAccessToken());
        newRoleComboBox.setValue(role);
    }
}