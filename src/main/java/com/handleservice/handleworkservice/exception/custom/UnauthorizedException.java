package com.handleservice.handleworkservice.exception.custom;

import org.springframework.http.HttpStatus;

/**
 * Thrown when a request is unauthorized
 */
public class UnauthorizedException extends DomainException {
    public UnauthorizedException(String message) {
        super(message);
    }

    @Override
    public int getHttpStatus() {
        return HttpStatus.UNAUTHORIZED.value();
    }
}
