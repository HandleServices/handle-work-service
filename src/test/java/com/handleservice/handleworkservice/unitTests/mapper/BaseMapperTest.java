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

    /**
     * Provides a pair of matching entity and DTO instances for testing.
     * @return A pair of matching entity and DTO instances.
     */
    protected abstract Pair<T, U> getMatchingValues();

    @BeforeEach
    public void setUp() {
        Pair<T, U> pair = getMatchingValues();
        expectedEntity = pair.first();
        expectedDTO = pair.second();
    }

    @Test
    public void testToEntity_shouldMappedDTOMatchConvertedEntityInCommonFields(){
        // Act
        T result = mapper.toEntity(expectedDTO);

        // Assert
        Assertions.assertEquals(expectedEntity, result);

    }

    @Test
    public void testToDTO_ShouldMappedEntityMatchConvertedDTOInCommonFields(){
        //Act
        U result = mapper.toDTO(expectedEntity);

        //Verify
        Assertions.assertEquals(expectedDTO, result);
    }
}
