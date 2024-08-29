package com.handleservice.handleworkservice.unitTests.validation;

import com.handleservice.handleworkservice.dto.work.CreateWorkDTO;

import java.math.BigDecimal;

public class CreateWorkDTOValidationTest extends BaseValidationTest<CreateWorkDTO> {
    @Override
    protected CreateWorkDTO getValidObject() {
        return new CreateWorkDTO(
                "Valid Name",
                "Valid description",
                "12:30",
                BigDecimal.valueOf(1)
        );
    }

    @Override
    protected CreateWorkDTO getInvalidObject() {
        return new CreateWorkDTO(
                "", // Invalid name: too short
                "",
                "25:61", // Invalid estimatedTime: does not match pattern
                BigDecimal.valueOf(100000000) // Invalid value: exceeds maximum
        );
    }

    @Override
    protected int getErrorsCount() {
        return 3;
    }
}
