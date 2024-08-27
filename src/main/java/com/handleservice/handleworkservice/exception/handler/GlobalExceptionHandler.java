package com.handleservice.handleworkservice.exception.handler;

import com.handleservice.handleworkservice.exception.custom.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    public record ErrorResponse(int status, String message) {
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> handleDomainExceptions(DomainException ex) {
        HttpStatus status = HttpStatus.valueOf(ex.getHttpStatus());
        ErrorResponse errorResponse = new ErrorResponse(ex.getHttpStatus(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, status);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            if (error instanceof FieldError) {
                String fieldErrorName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldErrorName, errorMessage);
            }
        });

        return errors;
    }

}