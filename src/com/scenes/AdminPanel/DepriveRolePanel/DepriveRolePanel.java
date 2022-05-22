package com.scenes.AdminPanel.DepriveRolePanel;

import com.App;
import com.assets.components.AutoCompleteComboBoxListener;
import com.assets.services.InteractingWithWindow;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.util.Map;

public class DepriveRolePanel {
    static Map<String, Integer> accounts;

    public static void showModal() {
        //Open needed window
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(DepriveRolePanel.class.getResource("DepriveRolePanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        //Requested roles and if failed to get, then exit, because often an error with the database
        accounts = Requests.getAccounts(App.getAccessToken());
        if (accounts != null) {
            ComboBox<String> accountsCB = (ComboBox) stage.getScene().lookup("#accountsComboBox");
            accountsCB.getItems().addAll(accounts.keySet());
            new AutoCompleteComboBoxListener<>(accountsCB);
        } else {
            ModalWindow.show("Error", "Database connection problem", ModalWindow.Icon.error);
            stage.close();
            return;
        }

        stage.show();

    }
}
