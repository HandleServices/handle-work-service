package com.handleservice.handleworkservice.unitTests.mapper;

import com.handleservice.handleworkservice.mapper.EntityMapper;
import com.handleservice.handleworkservice.utils.Pair;
import org.junit.jupiter.api.Test;

public abstract class BaseMapperTest<T, U> {

    protected final EntityMapper<T, U> mapper;

    protected BaseMapperTest(EntityMapper<T, U> mapper) {
        this.mapper = mapper;
    }

    protected abstract Pair<T, U> generatePair();

    @Test
    public abstract void testToEntity();

    @Test
    public abstract void testToDto();
}
