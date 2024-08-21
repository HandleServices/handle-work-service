package com.handleservice.handleworkservice.exception;

public class EntityNotFoundError extends RuntimeException {
    public EntityNotFoundError(String message) {
        super(message);
    }
}