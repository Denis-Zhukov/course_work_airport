package com.scenes.GeneralScenes.ModalWindow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ModalWindowController {
    @FXML
    Button closeBtn;

    public void closeWindow() {
        ((Stage)closeBtn.getScene().getWindow()).close();
    }
}
