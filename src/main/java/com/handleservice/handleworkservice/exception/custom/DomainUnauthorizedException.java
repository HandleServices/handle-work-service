package com.handleservice.handleworkservice.exception.custom;

import org.springframework.http.HttpStatus;

/**
 * Thrown when a request is unauthorized
 */
public class DomainUnauthorizedException extends DomainException {
    public DomainUnauthorizedException(String message) {
        super(message);
    }

    @Override
    public int getHttpStatus() {
        return HttpStatus.UNAUTHORIZED.value();
    }
}
