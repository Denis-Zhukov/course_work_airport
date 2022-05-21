package com.assets.services.Exceptions;

public class ResponseException extends CustomException {
    public ResponseException(Exception e, String suspendedMessage){
        super(e, suspendedMessage);
    }
}
