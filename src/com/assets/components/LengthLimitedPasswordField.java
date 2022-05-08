package com.assets.components;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LengthLimitedPasswordField extends PasswordField {
    private final SimpleIntegerProperty maxCharacters = new SimpleIntegerProperty();

    public LengthLimitedPasswordField() {
        final TextField thisField = this;
        this.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > maxCharacters.getValue())
                thisField.deleteNextChar();
        });
    }

    public void setMaxCharacters(int maxCharacters) {
        this.maxCharacters.set(maxCharacters);
    }

    public int getMaxCharacters() {
        return maxCharacters.getValue();
    }
}