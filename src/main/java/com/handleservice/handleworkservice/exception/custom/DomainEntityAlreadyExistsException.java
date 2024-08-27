package com.handleservice.handleworkservice.exception.custom;


import org.springframework.http.HttpStatus;

/**
 * Thrown when an entity with same unique fields already exists in database
 */
public class DomainEntityAlreadyExistsException extends DomainException {
    public DomainEntityAlreadyExistsException(String message) {
        super(message);
    }

    @Override
    public int getHttpStatus() {
        return HttpStatus.CONFLICT.value();
    }
}
