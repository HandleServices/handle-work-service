package com.handleservice.handleworkservice.exception.handler;

import com.handleservice.handleworkservice.exception.custom.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    public record ErrorResponse(HttpStatus statusCode, String message) {
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> handleBaseApiExceptions(DomainException ex) {
        HttpStatus status = HttpStatus.valueOf(ex.getHttpStatus());
        ErrorResponse errorResponse = new ErrorResponse(status, ex.getMessage());
        return new ResponseEntity<>(errorResponse, status);
    }
}