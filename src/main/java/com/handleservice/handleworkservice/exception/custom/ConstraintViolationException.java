package com.handleservice.handleworkservice.exception.custom;

import org.springframework.http.HttpStatus;

/**
 * Thrown when an operation violates a database constraint
 */
public class ConstraintViolationException extends DomainException {
    public ConstraintViolationException(String message) {
        super(message);
    }

    @Override
    public int getHttpStatus() {
        return HttpStatus.UNPROCESSABLE_ENTITY.value();
    }
}
