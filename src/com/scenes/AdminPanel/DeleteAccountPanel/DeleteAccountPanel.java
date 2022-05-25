package com.scenes.AdminPanel.DeleteAccountPanel;

import com.App;
import com.assets.components.AutoCompleteComboBoxListener;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.InteractingWithWindow;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.util.Map;

public class DeleteAccountPanel {
    public static Map<String, Integer> accounts;

    public static void showModal() {
        //Open needed window
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(DeleteAccountPanel.class.getResource("DeleteAccountPanel.fxml"));
        InteractingWithWindow.showModal(stage, loader);
        stage.centerOnScreen();

        //Requested accounts and if failed to get, then exit, because often an error with the database
        try {
            accounts = Requests.getAccounts(App.getAccessToken());
            ((ComboBox) stage.getScene().lookup("#accountsComboBox")).getItems().addAll(accounts.keySet());
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage() + "Role has not added", ModalWindow.Icon.error);
            ((Stage) stage.getScene().getWindow()).close();
            return;
        }
        stage.show();
    }
}
