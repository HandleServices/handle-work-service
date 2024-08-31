package com.handleservice.handleworkservice.exception.custom;

public abstract class DomainException extends RuntimeException {
    DomainException(String message) {
        super(message);
    }

    DomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getHttpStatus();
}
