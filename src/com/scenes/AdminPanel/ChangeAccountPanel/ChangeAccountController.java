package com.scenes.AdminPanel.ChangeAccountPanel;

import com.App;
import com.assets.components.AutoCompleteComboBoxListener;
import com.assets.components.LengthLimitedPasswordField;
import com.assets.components.LengthLimitedTextField;
import com.assets.services.Constants;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class ChangeAccountController {
    @FXML
    private ComboBox<String> usernameComboBox, newRoleComboBox;
    @FXML
    private LengthLimitedTextField loginField;
    @FXML
    private LengthLimitedPasswordField passwordField;

    public void submit() {
        String account = usernameComboBox.getValue();
        account = account == null ? "" : account;

        String newUsername = loginField.getText();
        String newPassword = passwordField.getText();

        String role = newRoleComboBox.getValue();
        role = role == null ? "" : role;

        //Validation data
        String error = "";
        if (!ChangeAccountPanel.accounts.containsKey(account))
            error += "Invalid current account\n";
        if (!Constants.regexLogin.matcher(newUsername).find())
            error += "Invalid new username\n";
        if (!(newPassword.equals("") || Constants.regexPassword.matcher(newPassword).find()))
            error += "Invalid new password\n";
        if (!ChangeAccountPanel.roles.containsKey(role))
            error += "Invalid new role\n";

        if (!error.equals("")) {
            ModalWindow.show("Error", error, ModalWindow.Icon.error);
            return;
        }

        //API Request
        try {
            int idAccount = ChangeAccountPanel.accounts.get(account);
            int idRole = ChangeAccountPanel.roles.get(role);
            Requests.updateAccount(
                    idAccount,
                    newUsername,
                    newPassword,
                    idRole,
                    App.getAccessToken()
            );

            //Reset fields and update combobox
            usernameComboBox.setValue("");
            loginField.setText("");
            passwordField.setText("");
            newRoleComboBox.setValue("");
            ChangeAccountPanel.accounts.remove(account);
            ChangeAccountPanel.accounts.put(newUsername, idAccount);

            ModalWindow.show("Success", "Account has updated", ModalWindow.Icon.success);
        } catch (ResponseException | NoServerResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage() + "\nAccount has not updated", ModalWindow.Icon.error);
        }
    }

    //Set fields account's info
    public void preloadAccount() {
        loginField.setText(usernameComboBox.getValue());
        String username = usernameComboBox.getValue();
        if (!ChangeAccountPanel.accounts.containsKey(username))
            return;
        String role = Requests.getRole(ChangeAccountPanel.accounts.get(username), App.getAccessToken());
        newRoleComboBox.setValue(role);
    }

    public void initialize() {
        new AutoCompleteComboBoxListener<>(usernameComboBox);
        new AutoCompleteComboBoxListener<>(newRoleComboBox);
    }
}
