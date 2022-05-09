package com.scenes.AdminPanel.DeleteRolePanel;

import com.App;
import com.assets.services.Requests;
import com.scenes.AdminPanel.DepriveRolePanel.DepriveRolePanel;
import com.scenes.ModalWindow.ModalWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class DeleteRoleController {
    @FXML
    ComboBox<String> rolesComboBox;

    public void submit() {
        String role = rolesComboBox.getValue();

        if(!DeleteRolePanel.roles.containsKey(role)){
            ModalWindow.show("Error", "Incorrect account", ModalWindow.Icon.error);
            return;
        }

        try {
            Requests.deleteRole(DeleteRolePanel.roles.get(role), App.getJWToken());
            rolesComboBox.getItems().remove(role);
            DeleteRolePanel.roles.remove(role);
            ModalWindow.show("Success", "Role deleted", ModalWindow.Icon.success);
        } catch (Exception e){
            ModalWindow.show("Error", e.getMessage(), ModalWindow.Icon.error);
        }
    }
}
