package com.assets.components;

import javafx.scene.input.KeyEvent;

public class NumberField extends LengthLimitedTextField {
    public NumberField() {
        this.addEventFilter(KeyEvent.KEY_TYPED, t -> {
            char arr[] = t.getCharacter().toCharArray();
            char ch = arr[t.getCharacter().toCharArray().length - 1];
            if (!(ch >= '0' && ch <= '9')) {
                t.consume();
            }
        });
    }

    public Integer getValue() {
        String value = getText();
        value = value.equals("") ? "0" : value;
        return Integer.parseInt(value);
    }
}
