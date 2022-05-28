package com.scenes.GeneralScenes.ModalWindow;

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
        try {
            //Load scene
            FXMLLoader loader = new FXMLLoader(ModalWindow.class.getResource("ModalWindow.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            //Load icon of modal window
            Image iconImg = null;
            switch (icon) {
                case success ->
                        iconImg = new Image(Objects.requireNonNull(ModalWindow.class.getResourceAsStream("/com/assets/images/modal-icons/success.png")));
                case error ->
                        iconImg = new Image(Objects.requireNonNull(ModalWindow.class.getResourceAsStream("/com/assets/images/modal-icons/error.png")));

            }

            //Set text of modal window
            ((TextArea) scene.lookup("#text")).setText(text);

            //Some properties
            stage.setTitle(title);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setResizable(false);

            //Modality
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

            //Set image of background and load icon of application
            ((ImageView) scene.lookup("#icon")).setImage(iconImg);
            Image windowIcon = new Image(Objects.requireNonNull(ModalWindow.class.getResourceAsStream("/com/assets/images/icon.png")));
            stage.getIcons().add(windowIcon);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            System.err.println("Failed to load initial application resources");
        }
    }

    //Possible icons
    public enum Icon {
        success,
        error
    }
}
