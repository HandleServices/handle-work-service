package com.handleservice.handleworkservice.unitTests.validator;

import com.handleservice.handleworkservice.dto.work.WorkDTO;

import java.math.BigDecimal;

public class WorkDTOValidationTest extends BaseValidationTest<WorkDTO> {
    @Override
    protected WorkDTO getValidObject() {
        return new WorkDTO(
                1L,
                "Valid name",
                "Valid description",
                BigDecimal.valueOf(1),
                "12:30",
                true
        );
    }

    @Override
    protected WorkDTO getInvalidObject() {
        return new WorkDTO(
                1L,
                "", // Invalid name: too short
                null,
                BigDecimal.valueOf(100000000), // Invalid value: exceeds maximum
                "25:61", // Invalid estimatedTime: does not match pattern
                true
        );
    }

    @Override
    protected int getErrorsCount() {
        return 3;
    }
}
