package com.scenes.AdminPanel.DeleteRolePanel;

import com.App;
import com.assets.services.InteractingWithWindow;
import com.assets.services.Requests;
import com.scenes.AdminPanel.DeleteAccountPanel.DeleteAccountPanel;
import com.scenes.ModalWindow.ModalWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.util.Map;

public class DeleteRolePanel {
    public static Map<String, Integer> roles;

    public static void showModal(){
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(DeleteRolePanel.class.getResource("DeleteRolePanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        ComboBox<String> rolesCB = (ComboBox)stage.getScene().lookup("#rolesComboBox");
        roles = Requests.getRoles(App.getJWToken());
        if(roles != null)
            rolesCB.getItems().addAll(roles.keySet());
        else {
            ModalWindow.show("Error", "Database connection problem", ModalWindow.Icon.error);
            stage.close();
            return;
        }

        stage.show();
    }
}
