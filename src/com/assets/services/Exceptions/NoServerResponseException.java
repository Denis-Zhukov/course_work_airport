package com.assets.services.Exceptions;

public class NoServerResponseException extends CustomException{
    public NoServerResponseException(Exception e, String suspendedMessage){
        super(e, suspendedMessage);
    }
}
