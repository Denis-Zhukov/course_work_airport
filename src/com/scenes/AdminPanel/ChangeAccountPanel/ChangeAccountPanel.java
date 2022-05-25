package com.scenes.AdminPanel.ChangeAccountPanel;

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

public class ChangeAccountPanel {
    public static Map<String, Integer> roles;
    public static Map<String, Integer> accounts;

    public static void showModal() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(ChangeAccountPanel.class.getResource("ChangeAccountPanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        try {
            ChangeAccountPanel.accounts = Requests.getAccounts(App.getAccessToken());
            ((ComboBox) stage.getScene().lookup("#usernameComboBox")).getItems().addAll(ChangeAccountPanel.accounts.keySet());

            ChangeAccountPanel.roles = Requests.getRoles(App.getAccessToken());
            ((ComboBox) stage.getScene().lookup("#newRoleComboBox")).getItems().addAll(ChangeAccountPanel.roles.keySet());
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage() + "Role has not added", ModalWindow.Icon.error);
            ((Stage) stage.getScene().getWindow()).close();
            return;
        }
        stage.show();
    }
}
