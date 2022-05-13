package com.assets.services;

public class NoServerResponseException extends Exception {

    private String suspendedMessage;
    public String getSuspendedMessage() {
        return suspendedMessage;
    }

    public NoServerResponseException(Exception e, String suspendedMessage) {
        setStackTrace(e.getStackTrace());
        initCause(e.getCause());
        this.suspendedMessage = suspendedMessage;
    }
}
