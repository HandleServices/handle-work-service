package com.handleservice.handleworkservice.unitTests.validation;

import com.handleservice.handleworkservice.dto.work.UpdateWorkDTO;

import java.math.BigDecimal;

public class UpdateWorkDTOValidationTest extends BaseValidationTest<UpdateWorkDTO> {

    @Override
    protected UpdateWorkDTO getValidObject() {
        return new UpdateWorkDTO(
                "Valid name",
                "Valid description",
                BigDecimal.valueOf(1),
                "12:30",
                true
        );
    }

    @Override
    protected UpdateWorkDTO getInvalidObject() {
        return new UpdateWorkDTO(
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
