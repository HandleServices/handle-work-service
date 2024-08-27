package com.handleservice.handleworkservice.mapper;

import org.mapstruct.Mapping;

public interface EntityMapper<T, U> {
    T toEntity(U dto);

    U toDTO(T entity);
}