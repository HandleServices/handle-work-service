package com.handleservice.handleworkservice.dto.work;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CreateWorkDTO(
        @NotBlank
        @Size(min = 2, max = 100, message = "The name is too short or too big")
        String name,

        @NotBlank
        @Nullable
        String description,

        @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "The estimatedTime must match hh:mm")
        String estimatedTime,

        @Max(value=99999999, message = "The value must not exceed 99999999")
        @Min(value = 0, message = "The value must not be negative")
        BigDecimal value
) {
}
