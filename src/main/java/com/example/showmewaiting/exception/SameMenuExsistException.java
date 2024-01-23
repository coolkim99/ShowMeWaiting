package com.example.showmewaiting.exception;

public class SameMenuExsistException extends RuntimeException{

    public SameMenuExsistException() {
        super();
    }

    public SameMenuExsistException(String message) {
        super(message);
    }
}
