package com.scenes.AdminPanel.DeleteAccountPanel;

import com.App;
import com.assets.services.AutoCompleteComboBoxListener;
import com.assets.services.InteractingWithWindow;
import com.assets.services.Requests;
import com.scenes.AdminPanel.CreateAccountPanel.CreateAccountPanel;
import com.scenes.ModalWindow.ModalWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.util.Map;

public class DeleteAccountPanel {
    public static Map<String, Integer> accounts;

    public static void showModal(){
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(DeleteAccountPanel.class.getResource("DeleteAccountPanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        ComboBox<String> accountsCB = (ComboBox)stage.getScene().lookup("#accountsComboBox");
        new AutoCompleteComboBoxListener<>(accountsCB);
        accounts = Requests.getAccounts(App.getJWToken());
        if(accounts != null)
            accountsCB.getItems().addAll(accounts.keySet());
        else {
            ModalWindow.show("Error", "Database connection problem", ModalWindow.Icon.error);
            stage.close();
            return;
        }

        stage.show();
    }
}
