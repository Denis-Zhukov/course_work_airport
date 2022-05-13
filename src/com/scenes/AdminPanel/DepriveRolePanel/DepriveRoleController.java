package com.scenes.AdminPanel.DepriveRolePanel;

import com.App;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class DepriveRoleController {
    @FXML
    ComboBox<String> accountsComboBox;

    public void submit() {
        String username = accountsComboBox.getValue();

        //Checking if the given username is in the loaded list from the database
        if(!DepriveRolePanel.accounts.containsKey(username)){
            ModalWindow.show("Error", "Incorrect account", ModalWindow.Icon.error);
            return;
        }

        //API Request
        try {
            Requests.depriveRole(DepriveRolePanel.accounts.get(username), App.getAccessToken());
            accountsComboBox.getItems().remove(username);
            DepriveRolePanel.accounts.remove(username);
            ModalWindow.show("Success", "Account deprived role", ModalWindow.Icon.success);
        } catch (Exception e){
            ModalWindow.show("Error", e.getMessage(), ModalWindow.Icon.error);
        }
    }
}
