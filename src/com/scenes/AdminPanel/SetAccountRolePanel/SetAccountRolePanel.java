package com.scenes.AdminPanel.SetAccountRolePanel;

import com.App;
import com.assets.services.InteractingWithWindow;
import com.assets.services.Requests;
import com.scenes.AdminPanel.CreateAccountPanel.CreateAccountPanel;
import com.scenes.ModalWindow.ModalWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class SetAccountRolePanel {
    public static void showModal(){
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(CreateAccountPanel.class.getResource("CreateAccountPanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();


        stage.show();
    }
}
