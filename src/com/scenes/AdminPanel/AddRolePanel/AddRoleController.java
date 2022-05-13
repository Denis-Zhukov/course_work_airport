package com.scenes.AdminPanel.AddRolePanel;

import com.App;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddRoleController {
    @FXML
    TextField roleField;

    public void submit() {
        String newRole = roleField.getText();

        //Validation
        if (newRole.equals("") || newRole.length() > 255) {
            ModalWindow.show("Error", "Role cannot be empty or longer than 255 characters", ModalWindow.Icon.error);
            return;
        }

        //API Request
        try {
            Requests.addRole(newRole, App.getAccessToken());
            ModalWindow.show("Success", "Role added", ModalWindow.Icon.success);
        } catch (Exception e) {
            ModalWindow.show("Error", e.getMessage(), ModalWindow.Icon.error);
        }
    }
}
