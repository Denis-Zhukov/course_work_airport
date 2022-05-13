package com.assets.services;

public class ResourceLoadingException extends Exception {

    private String suspendedMessage;
    public String getSuspendedMessage() {
        return suspendedMessage;
    }

    public ResourceLoadingException(Exception e, String suspendedMessage) {
        setStackTrace(e.getStackTrace());
        initCause(e.getCause());
        this.suspendedMessage = suspendedMessage;
    }
}
