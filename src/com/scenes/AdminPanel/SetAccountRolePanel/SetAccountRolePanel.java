package com.scenes.AdminPanel.SetAccountRolePanel;

import com.App;
import com.assets.services.AutoCompleteComboBoxListener;
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
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(SetAccountRolePanel.class.getResource("SetAccountRolePanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        ComboBox<String> loginsComboBox = (ComboBox<String>) stage.getScene().lookup("#accountsComboBox");
        if(loginsComboBox != null) {
            accounts = Requests.getAccounts(App.getJWToken());
            loginsComboBox.getItems().addAll(accounts.keySet());
            new AutoCompleteComboBoxListener<>(loginsComboBox);
        } else {
            ModalWindow.show("Error", "Database connection problem", ModalWindow.Icon.error);
            stage.close();
            return;
        }

        ComboBox<String> rolesCB = (ComboBox)stage.getScene().lookup("#rolesComboBox");
        roles = Requests.getRoles(App.getJWToken());
        if(roles != null)
            rolesCB.getItems().addAll(roles.keySet());
        else {
            ModalWindow.show("Error", "Database connection problem", ModalWindow.Icon.error);
            stage.close();
            return;
        }

        stage.show();
    }
}
