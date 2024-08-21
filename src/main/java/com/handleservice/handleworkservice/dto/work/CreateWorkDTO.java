package com.handleservice.handleworkservice.dto.work;

import java.math.BigDecimal;

public record CreateWorkDTO(
        String name,
        String description,
        BigDecimal value
) {
}
