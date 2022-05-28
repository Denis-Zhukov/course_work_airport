package com.scenes.AdminPanel.Accounts.DeleteAccountPanel;

import com.App;
import com.assets.components.AutoCompleteComboBoxListener;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.Requests;
import com.scenes.GeneralScenes.ModalWindow.ModalWindow;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class DeleteAccountController {
    @FXML
    private ComboBox<String> accountsComboBox;

    public void submit() {
        String username = accountsComboBox.getValue();
        username = username == null ? "" : username;

        //Validation data
        if (!DeleteAccountPanel.accounts.containsKey(username)) {
            ModalWindow.show("Error", "Incorrect account", ModalWindow.Icon.error);
            return;
        }

        //API Request
        try {
            Requests.deleteAccount(DeleteAccountPanel.accounts.get(username), App.getAccessToken());

            //Reset and update combobox
            accountsComboBox.getItems().remove(username);
            DeleteAccountPanel.accounts.remove(username);
            accountsComboBox.setValue("");

            ModalWindow.show("Success", "Account deleted", ModalWindow.Icon.success);
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage() + "\nAccount has not deleted", ModalWindow.Icon.error);
        }
    }

    public void initialize() {
        new AutoCompleteComboBoxListener<>(accountsComboBox);
    }
}
