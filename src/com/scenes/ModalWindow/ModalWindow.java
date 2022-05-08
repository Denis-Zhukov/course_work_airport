package com.scenes.ModalWindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ModalWindow {
    public static void show(String title, String text, Icon icon) {
        FXMLLoader loader = new FXMLLoader(ModalWindow.class.getResource("ModalWindow.fxml"));
        try {
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            Image iconImg = null;
            switch (icon) {
                case success -> {
                    iconImg = new Image(Objects.requireNonNull(ModalWindow.class.getResourceAsStream("/com/assets/images/modal-icons/success.png")));
                }
                case error -> {
                    iconImg = new Image(Objects.requireNonNull(ModalWindow.class.getResourceAsStream("/com/assets/images/modal-icons/error.png")));
                }
            }

            ((TextArea) scene.lookup("#text")).setText(text);
            ((ImageView) scene.lookup("#icon")).setImage(iconImg);

            Image ico = new Image(Objects.requireNonNull(ModalWindow.class.getResourceAsStream("/com/assets/images/icon.png")));
            stage.getIcons().add(ico);

            stage.setTitle(title);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setResizable(false);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public enum Icon {
        success,
        error
    }
}
