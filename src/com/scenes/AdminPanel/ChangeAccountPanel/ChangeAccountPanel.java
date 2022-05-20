package com.scenes.AdminPanel.ChangeAccountPanel;

import com.App;
import com.assets.services.AutoCompleteComboBoxListener;
import com.assets.services.InteractingWithWindow;
import com.assets.services.Requests;
import com.assets.services.ResourceLoadingException;
import com.scenes.ModalWindow.ModalWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.util.Map;

public class ChangeAccountPanel {
    public static Map<String, Integer> roles;
    public static Map<String, Integer> accounts;

    public static void showModal() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(ChangeAccountPanel.class.getResource("ChangeAccountPanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        //Requested roles and if failed to get, then exit, because often an error with the database
        ChangeAccountPanel.accounts = Requests.getAccounts(App.getAccessToken());
        if (accounts != null) {
            ComboBox<String> usersCB = (ComboBox) stage.getScene().lookup("#usernameComboBox");
            usersCB.getItems().addAll(ChangeAccountPanel.accounts.keySet());
            new AutoCompleteComboBoxListener<>(usersCB);
        } else {
            ModalWindow.show("Error", "Database connection problem", ModalWindow.Icon.error);
            stage.close();
            return;
        }

        ChangeAccountPanel.roles = Requests.getRoles(App.getAccessToken());
        if (roles != null) {
            ComboBox<String> rolesCB = (ComboBox) stage.getScene().lookup("#newRoleComboBox");
            rolesCB.getItems().addAll(ChangeAccountPanel.roles.keySet());
            new AutoCompleteComboBoxListener<>(rolesCB);
        } else {
            ModalWindow.show("Error", "Database connection problem", ModalWindow.Icon.error);
            stage.close();
            return;
        }

        stage.show();
    }
}
