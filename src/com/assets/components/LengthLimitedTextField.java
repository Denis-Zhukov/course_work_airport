package com.assets.components;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class LengthLimitedTextField extends TextField {
    private SimpleIntegerProperty maxCharacters = new SimpleIntegerProperty();

    public LengthLimitedTextField() {
        final TextField thisField = this;
        this.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() > maxCharacters.getValue())
                    thisField.deleteNextChar();
            }
        });
    }

    public void setMaxCharacters(int maxCharacters) {
        this.maxCharacters.set(maxCharacters);
    }

    public int getMaxCharacters() {
        return maxCharacters.getValue();
    }
}