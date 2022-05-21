package com.assets.services.Exceptions;

public class ResourceLoadingException extends CustomException {
    public ResourceLoadingException(Exception e, String suspendedMessage) {
        super(e, suspendedMessage);
    }
}
