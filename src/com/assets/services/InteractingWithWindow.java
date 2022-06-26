package com.assets.services;

import com.assets.services.Exceptions.ResourceLoadingException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class InteractingWithWindow {
    public static void changeScene(Stage stage, FXMLLoader loader) {
        try {
            Parent root = loader.load();
            stage.setScene(new Scene(root));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            System.out.println(String.format("Error loading %s: ", loader.getLocation()) + e.getMessage());
        }
    }

    public static void showModal(Stage stage, FXMLLoader loader) {
        try {
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);

            Image icon = new Image(Objects.requireNonNull(InteractingWithWindow.class.getResourceAsStream("/com/assets/images/icon.png")));
            stage.getIcons().add(icon);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static void showModal(FXMLLoader loader) throws ResourceLoadingException {
        showModal(new Stage(), loader);
    }
}
