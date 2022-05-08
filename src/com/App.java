package com;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {
    private static String JWToken;
    public static String getJWToken() {
        return JWToken;
    }
    public static void setJWTToken(String JWToken) {
        App.JWToken = JWToken;
    }

    private static String role;
    public static String getRole() {
        return role;
    }
    public static void setRole(String role) {
        App.role = role;
    }

    private static String username;
    public static String getUsername() {
        return username;
    }
    public static void setUsername(String username) {
        App.username = username;
    }

    public static void resetAccount() {
        setJWTToken(null);
        setRole(null);
        setUsername(null);
    }


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("App.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.setTitle("Air orders");

        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/assets/images/icon.png")));
        stage.getIcons().add(icon);

        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}