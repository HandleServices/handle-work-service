package com.handleservice.handleworkservice.dto.work;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record UpdateWorkDTO(
        @Size(min = 2, max = 100, message = "The name is too short or too big")
        String name,

        @Nullable
        String description,

        @Max(value=99999999, message = "The value must not exceed 99999999")
        @Min(value = 0, message = "The value must not be negative")
        BigDecimal value,

        @Pattern(regexp = "^([01][0-9]|2[0-3]):[0-5][0-9]$", message = "The estimatedTime must match hh:mm")
        String estimatedTime,

        boolean enable
) {
}