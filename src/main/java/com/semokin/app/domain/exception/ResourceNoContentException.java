package com.semokin.app.domain.exception;

public class ResourceNoContentException extends RuntimeException {

    public ResourceNoContentException(String message) {
        super(message);
    }
}
