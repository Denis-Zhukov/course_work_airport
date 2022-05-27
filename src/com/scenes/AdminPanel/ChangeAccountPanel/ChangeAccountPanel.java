package com.scenes.AdminPanel.ChangeAccountPanel;

import com.App;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.InteractingWithWindow;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.util.Map;

public class ChangeAccountPanel {
    public static Map<String, Integer> roles;
    public static Map<String, Integer> accounts;

    public static void showModal() {
        //Open needed window
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(ChangeAccountPanel.class.getResource("ChangeAccountPanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        try {
            //Load all usernames and their id
            ChangeAccountPanel.accounts = Requests.getAccounts(App.getAccessToken());
            ((ComboBox) stage.getScene().lookup("#usernameComboBox")).getItems().setAll(ChangeAccountPanel.accounts.keySet());
            //Load all roles and their id
            ChangeAccountPanel.roles = Requests.getRoles(App.getAccessToken());
            ((ComboBox) stage.getScene().lookup("#newRoleComboBox")).getItems().setAll(ChangeAccountPanel.roles.keySet());
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage(), ModalWindow.Icon.error);
            ((Stage) stage.getScene().getWindow()).close();
            return;
        }

        stage.show();
    }
}
