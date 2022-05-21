package com.assets.services.Exceptions;

public class CustomException extends Exception {
    private final String suspendedMessage;
    public String getSuspendedMessage() {
        return suspendedMessage;
    }

    public CustomException(Exception e, String suspendedMessage) {
        if (e != null) {
            setStackTrace(e.getStackTrace());
            initCause(e.getCause());
        }
        this.suspendedMessage = suspendedMessage;
    }
}
