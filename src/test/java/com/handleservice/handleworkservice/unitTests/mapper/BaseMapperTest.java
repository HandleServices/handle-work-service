package com.handleservice.handleworkservice.unitTests.mapper;

import com.handleservice.handleworkservice.mapper.EntityMapper;
import com.handleservice.handleworkservice.utils.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public abstract class BaseMapperTest<T, U> {

    protected final EntityMapper<T, U> mapper;

    protected BaseMapperTest(EntityMapper<T, U> mapper) {
        this.mapper = mapper;
    }

    protected abstract Pair<T, U> getMatchingValues();

    @Test
    public void testToEntity(){
        Pair<T, U> pair = getMatchingValues();
        T expectedEntity = pair.first();
        U expectedDto = pair.second();

        T result = mapper.toEntity(expectedDto);

        Assertions.assertEquals(expectedEntity, result);

    }

    @Test
    public void testToDto(){
        Pair<T, U> pair = getMatchingValues();
        T expectedEntity = pair.first();
        U expectedDto = pair.second();

        U result = mapper.toDTO(expectedEntity);

        Assertions.assertEquals(expectedDto, result);
    }
}
