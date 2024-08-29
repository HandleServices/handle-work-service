package com.handleservice.handleworkservice.unitTests.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class BaseValidationTest<T> {

    protected static Validator validator;

    protected T validObject;
    protected T invalidObject;
    protected int errorsCount;

    protected abstract T getValidObject();

    protected abstract T getInvalidObject();

    protected abstract int getErrorsCount();

    @BeforeAll
    public static void setUpAll() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @BeforeEach
    public void setUp() {
        // Preparation
        validObject = getValidObject();
        invalidObject = getInvalidObject();
        errorsCount = getErrorsCount();
    }

    @Test
    public void testValid_givenValidData_shouldNotHaveErrors() {
        // Act
        Set<ConstraintViolation<T>> violations = validator.validate(validObject);

        // Assert
        assertEquals(0, violations.size());
    }

    @Test
    public void testValid_givenInvalidData_shouldHaveErrors() {
        // Act
        Set<ConstraintViolation<T>> violations = validator.validate(invalidObject);

        // Assert
        assertEquals(errorsCount, violations.size());
    }


}
