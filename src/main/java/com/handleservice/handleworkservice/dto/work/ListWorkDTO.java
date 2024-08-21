package com.handleservice.handleworkservice.dto.work;

import jakarta.annotation.Nullable;

import java.math.BigDecimal;

public record ListWorkDTO(
        String name,
        @Nullable String description,
        BigDecimal value,
        Boolean enable
) {
}