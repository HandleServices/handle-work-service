package com.handleservice.handleworkservice.dto.work;

import jakarta.annotation.Nullable;

import java.math.BigDecimal;

public record CreateWorkDTO(
        String name,
        @Nullable String description,
        String estimatedTime,
        BigDecimal value
) {
}
