package com.scenes.LoginPanel;

import com.App;
import com.assets.services.Constants;
import com.assets.services.InteractingWithWindow;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.Locale;

import static com.assets.services.Constants.regexLogin;
import static com.assets.services.Constants.regexPassword;

public class LoginController {
    @FXML
    TextField loginField;
    @FXML
    PasswordField passwordField;
    @FXML
    Button backBtn;

    public void signIn() {
        String login = loginField.getText();
        String password = passwordField.getText();

        String error = "";
        if (!regexLogin.matcher(login).find())
            error += "Wrong login\n";

        if (!regexPassword.matcher(password).find())
            error += "Wrong password";

        if (!error.equals("")) {
            ModalWindow.show("Error", error, ModalWindow.Icon.error);
            return;
        }

        var json = Requests.getJWTToken(login, password);

        if (json.isNull("token"))
            ModalWindow.show("Error", "Wrong login or password", ModalWindow.Icon.error);
        else {
            App.setJWTToken(json.getString("token"));
            App.setRole(json.getString("role").toLowerCase(Locale.ROOT));
            App.setUsername(json.getString("username"));
            Stage stage = (Stage) backBtn.getScene().getWindow();
            if (Constants.scenesByRoles.containsKey(App.getRole()))
                Constants.scenesByRoles.get(App.getRole()).accept(stage);
            else
                ModalWindow.show("Error", "Authorization completed successfully.\nBut the " + App.getRole() + " role has no window to display\n", ModalWindow.Icon.error);
        }
    }

    public void backToEnter() {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(App.class.getResource("App.fxml"));
        InteractingWithWindow.changeScene(stage, loader);
        stage.centerOnScreen();
    }

    public void enterPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() != KeyCode.ENTER) return;
        signIn();
    }
}
