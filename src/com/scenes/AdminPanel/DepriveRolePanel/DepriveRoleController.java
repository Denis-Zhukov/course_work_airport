package com.scenes.AdminPanel.DepriveRolePanel;

import com.App;
import com.assets.services.Requests;
import com.scenes.AdminPanel.DeleteAccountPanel.DeleteAccountPanel;
import com.scenes.ModalWindow.ModalWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class DepriveRoleController {
    @FXML
    ComboBox<String> accountsComboBox;

    public void submit() {
        String username = accountsComboBox.getValue();

        if(!DepriveRolePanel.accounts.containsKey(username)){
            ModalWindow.show("Error", "Incorrect account", ModalWindow.Icon.error);
            return;
        }

        try {
            Requests.depriveRole(DepriveRolePanel.accounts.get(username), App.getJWToken());
            accountsComboBox.getItems().remove(username);
            DepriveRolePanel.accounts.remove(username);
            ModalWindow.show("Success", "Account deprived role", ModalWindow.Icon.success);
        } catch (Exception e){
            ModalWindow.show("Error", e.getMessage(), ModalWindow.Icon.error);
        }
    }
}
