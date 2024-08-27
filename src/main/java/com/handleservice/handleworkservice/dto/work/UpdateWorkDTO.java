package com.handleservice.handleworkservice.dto.work;

import jakarta.annotation.Nullable;

import java.math.BigDecimal;

public record UpdateWorkDTO(
        String name,
        @Nullable String description,
        BigDecimal value,
        String estimatedTime,
        boolean enable
) {
}