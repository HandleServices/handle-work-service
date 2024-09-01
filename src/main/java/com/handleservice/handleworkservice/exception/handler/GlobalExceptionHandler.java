package com.handleservice.handleworkservice.exception.handler;

import com.handleservice.handleworkservice.exception.custom.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    public record ErrorResponse(int status, String message) {
    }

    public record DetailedErrorResponse(int status, String message, Map<String, String> details) {
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleDefaultException() {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = new ErrorResponse(status.value(), "Internal Server Error");
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> handleDomainExceptions(DomainException ex) {
        HttpStatus status = HttpStatus.valueOf(ex.getHttpStatus());
        ErrorResponse errorResponse = new ErrorResponse(ex.getHttpStatus(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DetailedErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        Map<String, String> errors = ex.getBindingResult().getAllErrors().stream()
                .filter(error -> error instanceof FieldError)
                .map(error -> (FieldError) error)
                .collect(Collectors.toMap(
                        FieldError::getField,
                        error -> Optional.ofNullable(error.getDefaultMessage()).orElse("")
                ));

        DetailedErrorResponse errorResponse = new DetailedErrorResponse(status.value(), "Unprocessable entity", errors);
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(Exception ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = new ErrorResponse(status.value(), "Unprocessable Entity: " + ex.getMessage());
        return new ResponseEntity<>(errorResponse, status);
    }

}