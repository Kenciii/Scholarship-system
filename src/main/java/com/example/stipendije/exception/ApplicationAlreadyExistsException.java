package com.example.stipendije.exception;

public class ApplicationAlreadyExistsException extends RuntimeException {

    public ApplicationAlreadyExistsException(String message) {
        super(message);
    }
}
