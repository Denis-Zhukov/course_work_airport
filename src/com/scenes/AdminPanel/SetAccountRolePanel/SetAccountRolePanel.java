package com.scenes.AdminPanel.SetAccountRolePanel;

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

public class SetAccountRolePanel {
    public static Map<String, Integer> accounts;
    public static Map<String, Integer> roles;

    public static void showModal() {
        //Open need window
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(SetAccountRolePanel.class.getResource("SetAccountRolePanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();


        try {
            //Load all usernames and their id
            accounts = Requests.getAccounts(App.getAccessToken());
            ((ComboBox<String>) stage.getScene().lookup("#accountsComboBox")).getItems().setAll(accounts.keySet());
            //Load all roles and their id
            roles = Requests.getRoles(App.getAccessToken());
            ((ComboBox<String>) stage.getScene().lookup("#rolesComboBox")).getItems().setAll(roles.keySet());
        } catch (ResponseException | NoServerResponseException e) {
            ModalWindow.show("Error", "Database connection problem", ModalWindow.Icon.error);
            stage.close();
            return;
        }
        stage.show();
    }
}
