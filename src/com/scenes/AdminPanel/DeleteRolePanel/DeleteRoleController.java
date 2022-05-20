package com.scenes.AdminPanel.DeleteRolePanel;

import com.App;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class DeleteRoleController {
    @FXML
    private ComboBox<String> rolesComboBox;

    public void submit() {
        String role = rolesComboBox.getValue();

        //Checking if the given role is in the loaded list from the database
        if (!DeleteRolePanel.roles.containsKey(role)) {
            ModalWindow.show("Error", "Incorrect account", ModalWindow.Icon.error);
            return;
        }

        //API Request
        try {
            Requests.deleteRole(DeleteRolePanel.roles.get(role), App.getAccessToken());
            rolesComboBox.getItems().remove(role);
            DeleteRolePanel.roles.remove(role);
            ModalWindow.show("Success", "Role deleted", ModalWindow.Icon.success);
            rolesComboBox.setValue(null);
        } catch (Exception e) {
            ModalWindow.show("Error", e.getMessage(), ModalWindow.Icon.error);
        }
    }
}
