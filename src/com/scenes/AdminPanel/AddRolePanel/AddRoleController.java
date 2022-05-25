package com.scenes.AdminPanel.AddRolePanel;

import com.App;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddRoleController {
    @FXML
    private TextField roleField;

    public void submit() {
        String newRole = roleField.getText().trim();

        //Validation
        if (newRole.equals("") || newRole.length() > 255) {
            ModalWindow.show("Error", "Role cannot be empty or longer than 255 characters", ModalWindow.Icon.error);
            return;
        }

        //API Request
        try {
            Requests.addRole(newRole, App.getAccessToken());
            ModalWindow.show("Success", "Role has added", ModalWindow.Icon.success);
            roleField.setText("");
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage()+"\nRole has not added", ModalWindow.Icon.error);
        }
    }
}
