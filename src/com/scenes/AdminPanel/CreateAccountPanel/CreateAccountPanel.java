package com.scenes.AdminPanel.CreateAccountPanel;

import com.App;
import com.assets.components.AutoCompleteComboBoxListener;
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

        //Requested accounts and if failed to get, then exit, because often an error with the database
        try {
            roles = Requests.getRoles(App.getAccessToken());
            ((ComboBox) stage.getScene().lookup("#rolesComboBox")).getItems().addAll(roles.keySet());
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage() + "Role has not added", ModalWindow.Icon.error);
            ((Stage) stage.getScene().getWindow()).close();
            return;
        }
        stage.show();
    }
}
