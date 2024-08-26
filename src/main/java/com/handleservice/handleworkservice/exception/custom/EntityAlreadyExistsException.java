package com.handleservice.handleworkservice.exception.custom;


import org.springframework.http.HttpStatus;

/**
 * Thrown when an entity with same unique fields already exists in database
 */
public class EntityAlreadyExistsException extends DomainException {
    public EntityAlreadyExistsException(String message) {
        super(message);
    }

    @Override
    public int getHttpStatus() {
        return HttpStatus.CONFLICT.value();
    }
}
