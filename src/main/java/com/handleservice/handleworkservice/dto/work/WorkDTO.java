package com.handleservice.handleworkservice.dto.work;

import jakarta.annotation.Nullable;

import java.math.BigDecimal;

public record WorkDTO(
        long id,
        String name,
        @Nullable String description,
        BigDecimal value,
        boolean enable
) {
}