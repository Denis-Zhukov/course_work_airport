package com.scenes.AdminPanel.Accounts.CreateAccountPanel;

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

public class CreateAccountPanel {
    public static Map<String, Integer> roles;

    public static void showModal() {
        //Open needed window
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(CreateAccountPanel.class.getResource("CreateAccountPanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        try {
            //Load all usernames and their id
            roles = Requests.getRoles(App.getAccessToken());
            ((ComboBox) stage.getScene().lookup("#rolesComboBox")).getItems().setAll(roles.keySet());
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage(), ModalWindow.Icon.error);
            ((Stage) stage.getScene().getWindow()).close();
            return;
        }

        stage.show();
    }
}
