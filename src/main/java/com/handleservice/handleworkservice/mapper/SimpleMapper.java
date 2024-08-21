package com.handleservice.handleworkservice.mapper;

import org.modelmapper.ModelMapper;

import java.util.List;

public class SimpleMapper<T, U> implements Mapper<T, U> {

    protected final Class<U> dtoClass;
    protected final Class<T> entityClass;

    protected final ModelMapper modelMapper = new ModelMapper();

    public SimpleMapper(Class<U> dtoClass, Class<T> entityClass) {
        this.dtoClass = dtoClass;
        this.entityClass = entityClass;
    }

    @Override
    public U toDTO(T entity) {
        return modelMapper.map(entity, dtoClass);
    }

    @Override
    public T toEntity(U dto) {
        return modelMapper.map(dto, entityClass);
    }

    @Override
    public List<U> toDTOList(List<T> entities) {
        return entities.stream().map(this::toDTO).toList();
    }

    @Override
    public List<T> toEntityList(List<U> dtos) {
        return dtos.stream().map(this::toEntity).toList();
    }
}
