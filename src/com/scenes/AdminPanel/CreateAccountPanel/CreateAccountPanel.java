package com.scenes.AdminPanel.CreateAccountPanel;

import com.App;
import com.assets.services.InteractingWithWindow;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.util.Map;

public class CreateAccountPanel {
    public static Map<String, Integer> roles;

    public static void showModal(){
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(CreateAccountPanel.class.getResource("CreateAccountPanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        ComboBox<String> rolesCB = (ComboBox)stage.getScene().lookup("#rolesComboBox");
        roles = Requests.getRoles(App.getJWToken());
        if(roles != null)
        rolesCB.getItems().addAll(roles.keySet());
        else ModalWindow.show("Error", "Database connection problem", ModalWindow.Icon.error);

        stage.show();
    }
}
