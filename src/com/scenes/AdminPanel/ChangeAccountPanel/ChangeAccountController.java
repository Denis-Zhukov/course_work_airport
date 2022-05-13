package com.scenes.AdminPanel.ChangeAccountPanel;

import com.App;
import com.assets.components.LengthLimitedPasswordField;
import com.assets.components.LengthLimitedTextField;
import com.assets.services.Constants;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class ChangeAccountController {
    @FXML
    ComboBox<String> usernameComboBox;
    @FXML
    LengthLimitedTextField loginField;
    @FXML
    LengthLimitedPasswordField passwordField;
    @FXML
    ComboBox<String> newRoleComboBox;

    public void submit() {
        String account = usernameComboBox.getValue();
        String newUsername = loginField.getText();
        String newPassword = passwordField.getText();
        String role = newRoleComboBox.getValue();

        //Validation data
        String error = "";
        if (account == null || !Constants.regexLogin.matcher(account).find())
            error += "Invalid current account\n";
        if (!Constants.regexLogin.matcher(newUsername).find())
            error += "Invalid new username\n";
        if (!(newPassword.equals("") || Constants.regexPassword.matcher(newPassword).find()))
            error += "Invalid new password\n";
        if (role == null || !ChangeAccountPanel.roles.containsKey(role))
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
            ((Stage) usernameComboBox.getScene().getWindow()).close();
        } catch (Exception e) {
            ModalWindow.show("Error", e.getMessage(), ModalWindow.Icon.error);
        }
    }

    public void preloadAccount() {
        loginField.setText(usernameComboBox.getValue());
        String role = Requests.getRole(ChangeAccountPanel.accounts.get(usernameComboBox.getValue()), App.getAccessToken());
        newRoleComboBox.setValue(role);
    }
}
