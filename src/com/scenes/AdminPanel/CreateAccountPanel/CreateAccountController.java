package com.scenes.AdminPanel.CreateAccountPanel;

import com.App;
import com.assets.components.LengthLimitedPasswordField;
import com.assets.components.LengthLimitedTextField;
import com.assets.services.Constants;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
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

        //Validation data
        String error = "";
        if (!Constants.regexLogin.matcher(login).find())
            error += "Invalid login\n";
        if (!Constants.regexPassword.matcher(password).find())
            error += "Invalid password\n";
        if (!CreateAccountPanel.roles.containsKey(role))
            error += "Incorrect role\n";

        if (!error.equals("")) {
            ModalWindow.show("Error", error, ModalWindow.Icon.error);
            return;
        }

        //API Request
        try {
            Requests.addAccount(login, password, CreateAccountPanel.roles.get(role), App.getAccessToken());
            ModalWindow.show("Success", "Account added", ModalWindow.Icon.success);
            rolesComboBox.setValue(null);
            loginField.setText("");
            passwordField.setText("");
        } catch (Exception e){
            ModalWindow.show("Error", e.getMessage(), ModalWindow.Icon.error);
        }
    }
}
