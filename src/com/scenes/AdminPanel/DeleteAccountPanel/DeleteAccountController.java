package com.scenes.AdminPanel.DeleteAccountPanel;

import com.App;
import com.assets.services.Requests;
import com.scenes.AdminPanel.CreateAccountPanel.CreateAccountPanel;
import com.scenes.ModalWindow.ModalWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class DeleteAccountController {
    @FXML
    ComboBox<String> accountsComboBox;

    public void submit() {
        String username = accountsComboBox.getValue();

        if(!DeleteAccountPanel.accounts.containsKey(username)){
            ModalWindow.show("Error", "Incorrect account", ModalWindow.Icon.error);
            return;
        }

        try {
            Requests.deleteAccount(DeleteAccountPanel.accounts.get(username), App.getJWToken());
            accountsComboBox.getItems().remove(username);
            DeleteAccountPanel.accounts.remove(username);
            ModalWindow.show("Success", "Account deleted", ModalWindow.Icon.success);
        } catch (Exception e){
            ModalWindow.show("Error", e.getMessage(), ModalWindow.Icon.error);
        }
    }
}
