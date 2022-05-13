package com;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {
    //JWT Token for authorization
    private static String accessToken;
    public static String getAccessToken() {
        return accessToken;
    }
    public static void setAccessToken(String accessToken) {
        App.accessToken = accessToken;
    }

    private static String refreshToken;
    public static String getRefreshToken() {
        return refreshToken;
    }
    public static void setRefreshToken(String refreshToken) {
        App.refreshToken = refreshToken;
    }

    //Username of account
    private static String username;
    public static String getUsername() {
        return username;
    }
    public static void setUsername(String username) {
        App.username = username;
    }

    //Role of account
    private static String role;
    public static String getRole() {
        return role;
    }
    public static void setRole(String role) {
        App.role = role;
    }

    //Reset all info of account
    public static void resetAccount() {
        setAccessToken(null);
        setUsername(null);
        setRole(null);
    }


    @Override
    public void start(Stage stage) {
        try {
            //Load fxml scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("App.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.setTitle("Air orders");

            stage.setResizable(false);
            stage.show();

            //Load icon for application
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/assets/images/icon.png")));
            stage.getIcons().add(icon);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            System.err.println("Failed to load initial application resources.");
            //LOGER!!!
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}