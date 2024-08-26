package com.handleservice.handleworkservice.exception.custom;

import org.springframework.http.HttpStatus;

/**
 * Thrown when an operation violates a Business Rule
 */
public class BusinessLogicViolationException extends DomainException {
    public BusinessLogicViolationException(String message) {
        super(message);
    }

    @Override
    public int getHttpStatus() {
        return HttpStatus.BAD_REQUEST.value();
    }
}
