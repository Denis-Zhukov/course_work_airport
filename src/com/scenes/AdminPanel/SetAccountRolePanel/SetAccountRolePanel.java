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
        //Open need window
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(SetAccountRolePanel.class.getResource("SetAccountRolePanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();


        //Requested accounts and if failed to get, then exit, because often an error with the database
        accounts = Requests.getAccounts(App.getAccessToken());
        if (accounts != null) {
            ComboBox<String> loginsComboBox = (ComboBox<String>) stage.getScene().lookup("#accountsComboBox");
            loginsComboBox.getItems().addAll(accounts.keySet());
            new AutoCompleteComboBoxListener<>(loginsComboBox);
        } else {
            ModalWindow.show("Error", "Database connection problem", ModalWindow.Icon.error);
            stage.close();
            return;
        }

        //Requested roles and if failed to get, then exit, because often an error with the database
        roles = Requests.getRoles(App.getAccessToken());
        if (roles != null) {
            ComboBox<String> rolesCB = (ComboBox) stage.getScene().lookup("#rolesComboBox");
            rolesCB.getItems().addAll(roles.keySet());
        } else {
            ModalWindow.show("Error", "Database connection problem", ModalWindow.Icon.error);
            stage.close();
            return;
        }

        stage.show();
    }
}
