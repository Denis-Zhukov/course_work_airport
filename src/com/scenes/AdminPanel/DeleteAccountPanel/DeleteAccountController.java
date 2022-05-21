package com.scenes.AdminPanel.DeleteAccountPanel;

import com.App;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class DeleteAccountController {
    @FXML
    private ComboBox<String> accountsComboBox;

    public void submit() {
        String username = accountsComboBox.getValue();
        username = username == null ? "" : username.trim();

        //Checking if the given account is in the loaded list from the database
        if (!DeleteAccountPanel.accounts.containsKey(username)) {
            ModalWindow.show("Error", "Incorrect account", ModalWindow.Icon.error);
            return;
        }

        //API Request
        try {
            Requests.deleteAccount(DeleteAccountPanel.accounts.get(username), App.getAccessToken());
            accountsComboBox.getItems().remove(username);
            DeleteAccountPanel.accounts.remove(username);
            ModalWindow.show("Success", "Account deleted", ModalWindow.Icon.success);
            accountsComboBox.setValue("");
        } catch (Exception e) {
            ModalWindow.show("Error", e.getMessage(), ModalWindow.Icon.error);
        }
    }
}
