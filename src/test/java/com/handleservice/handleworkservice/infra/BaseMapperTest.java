package com.handleservice.handleworkservice.infra;

import com.handleservice.handleworkservice.mapper.EntityMapper;
import com.handleservice.handleworkservice.utils.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class BaseMapperTest<T, U> {

    protected final EntityMapper<T, U> mapper;

    protected T expectedEntity;
    protected U expectedDTO;

    protected BaseMapperTest(EntityMapper<T, U> mapper) {
        this.mapper = mapper;
    }

    protected abstract Pair<T, U> getMatchingValues();

    @BeforeEach
    public void setUp() {
        Pair<T, U> pair = getMatchingValues();
        expectedEntity = pair.first();
        expectedDTO = pair.second();
    }

    @Test
    public void testToEntity(){
        T result = mapper.toEntity(expectedDTO);

        Assertions.assertEquals(expectedEntity, result);

    }

    @Test
    public void testToDto(){
        U result = mapper.toDTO(expectedEntity);

        Assertions.assertEquals(expectedDTO, result);
    }
}
