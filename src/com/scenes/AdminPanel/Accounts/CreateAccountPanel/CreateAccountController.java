package com.scenes.AdminPanel.Accounts.CreateAccountPanel;

import com.App;
import com.assets.components.AutoCompleteComboBoxListener;
import com.assets.components.LengthLimitedPasswordField;
import com.assets.components.LengthLimitedTextField;
import com.assets.services.Constants;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.Requests;
import com.scenes.GeneralScenes.ModalWindow.ModalWindow;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class CreateAccountController {
    @FXML
    private ComboBox<String> rolesComboBox;
    @FXML
    private LengthLimitedTextField loginField;
    @FXML
    private LengthLimitedPasswordField passwordField;


    public void submit() {
        String login = loginField.getText();
        String password = passwordField.getText();

        String role = rolesComboBox.getValue();
        role = role == null ? "" : role;

        //Validation data
        String error = "";
        if (!Constants.regexLogin.matcher(login).find())
            error += "Invalid login\n";
        if (!Constants.regexPassword.matcher(password).find())
            error += "Invalid password\n";
        if (!CreateAccountPanel.roles.containsKey(role))
            error += "Invalid role\n";

        if (!error.equals("")) {
            ModalWindow.show("Error", error, ModalWindow.Icon.error);
            return;
        }

        //API Request
        try {
            Requests.addAccount(login, password, CreateAccountPanel.roles.get(role), App.getAccessToken());

            //Reset fields
            rolesComboBox.setValue("");
            loginField.setText("");
            passwordField.setText("");

            ModalWindow.show("Success", "Account has created", ModalWindow.Icon.success);
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage() + "\nAccount has not created", ModalWindow.Icon.error);
        }
    }

    public void initialize() {
        new AutoCompleteComboBoxListener<>(rolesComboBox);
    }
}
