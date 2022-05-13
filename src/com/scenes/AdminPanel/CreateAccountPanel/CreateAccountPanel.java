package com.scenes.AdminPanel.CreateAccountPanel;

import com.App;
import com.assets.services.AutoCompleteComboBoxListener;
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
        //Open needed window
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(CreateAccountPanel.class.getResource("CreateAccountPanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        //Requested accounts and if failed to get, then exit, because often an error with the database
        roles = Requests.getRoles(App.getAccessToken());
        if(roles != null) {
            ComboBox<String> rolesCB = (ComboBox)stage.getScene().lookup("#rolesComboBox");
            rolesCB.getItems().addAll(roles.keySet());
            new AutoCompleteComboBoxListener<>(rolesCB);
        }
        else {
            ModalWindow.show("Error", "Database connection problem", ModalWindow.Icon.error);
            stage.close();
            return;
        }

        stage.show();
    }
}
