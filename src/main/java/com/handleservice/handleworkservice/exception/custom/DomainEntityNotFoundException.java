package com.handleservice.handleworkservice.exception.custom;

import org.springframework.http.HttpStatus;

/**
 * Thrown when a specific entity is not found in the database.
 */
public class DomainEntityNotFoundException extends DomainException {
    public DomainEntityNotFoundException(String message) {
        super(message);
    }

    @Override
    public int getHttpStatus() {
        return HttpStatus.NOT_FOUND.value();
    }
}