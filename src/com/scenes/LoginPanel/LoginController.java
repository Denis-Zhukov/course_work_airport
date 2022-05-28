package com.scenes.LoginPanel;

import com.App;
import com.assets.services.Constants;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.InteractingWithWindow;
import com.assets.services.Requests;
import com.scenes.GeneralScenes.ModalWindow.ModalWindow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.util.Locale;

import static com.assets.services.Constants.regexLogin;
import static com.assets.services.Constants.regexPassword;

public class LoginController {
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;

    public void signIn() {
        String login = loginField.getText();
        String password = passwordField.getText();

        //Validation
        String error = "";
        if (!regexLogin.matcher(login).find())
            error += "Wrong login\n";

        if (!regexPassword.matcher(password).find())
            error += "Wrong password\n";

        if (!error.equals("")) {
            ModalWindow.show("Error", error, ModalWindow.Icon.error);
            return;
        }

        //Authorization
        JSONObject responseJson = null;
        try {
            responseJson = Requests.getJWTToken(login, password);
        } catch (NoServerResponseException e) {
            ModalWindow.show("Error", "Suspended message: " + e.getSuspendedMessage() + "\nException message: " + e.getCause().getMessage(), ModalWindow.Icon.error);
            return;
        }

        //Handling server responses
        switch (responseJson.getInt("statusServer")) {
            case 500, 401, 400 -> ModalWindow.show("Error", responseJson.getString("message"), ModalWindow.Icon.error);
            case 200 -> {
                App.setAccessToken(responseJson.getString("token"));
                App.setRole(responseJson.getString("role").toLowerCase(Locale.ROOT));
                App.setUsername(responseJson.getString("username"));

                Stage stage = (Stage) loginField.getScene().getWindow();
                if (Constants.scenesByRoles.containsKey(App.getRole()))
                    Constants.scenesByRoles.get(App.getRole()).accept(stage);
                else
                    ModalWindow.show("Error", "Authorization completed successfully.\nBut the " + App.getRole() + " role has no window to display.\nYou may have an outdated version of the program.\n", ModalWindow.Icon.error);
            }
        }
    }

    //Back to main menu
    public void backToEnter() {
        Stage stage = (Stage) loginField.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(App.class.getResource("App.fxml"));
        InteractingWithWindow.changeScene(stage, loader);
        stage.centerOnScreen();
    }

    //Sign in on press enter by field
    public void enterPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() != KeyCode.ENTER) return;
        signIn();
    }
}
