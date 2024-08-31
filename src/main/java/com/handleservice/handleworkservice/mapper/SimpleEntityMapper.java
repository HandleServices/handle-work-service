package com.handleservice.handleworkservice.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.util.Assert;

public class SimpleEntityMapper<T, U> implements EntityMapper<T, U> {

    protected final Class<U> dtoClass;
    protected final Class<T> entityClass;

    protected final ModelMapper modelMapper = new ModelMapper();

    public SimpleEntityMapper(Class<T> entityClass, Class<U> dtoClass) throws IllegalArgumentException {
        Assert.isTrue(!entityClass.isRecord(), "entityClass cannot be a record");
        Assert.isTrue(!dtoClass.isRecord(), "dtoClass cannot be a record");
        this.dtoClass = dtoClass;
        this.entityClass = entityClass;
    }

    public U toDTO(T entity) {
        return modelMapper.map(entity, dtoClass);
    }

    public T toEntity(U dto) {
        return modelMapper.map(dto, entityClass);
    }
}
