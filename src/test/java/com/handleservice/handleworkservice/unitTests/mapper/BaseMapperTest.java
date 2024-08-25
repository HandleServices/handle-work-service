package com.handleservice.handleworkservice.unitTests.mapper;

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
        // Given: A DTO of type U
        // When: We try to convert it to an entity of type T
        T result = mapper.toEntity(expectedDTO);

        // Then: the converted entity of type T should match the DTO on the common fields
        Assertions.assertEquals(expectedEntity, result);

    }

    @Test
    public void testToDto(){
        // Given: An entity of type T
        // When: We try to convert it to a DTO of type U
        U result = mapper.toDTO(expectedEntity);

        // Then: the converted DTO of type U should match the entity on the common fields
        Assertions.assertEquals(expectedDTO, result);
    }
}
