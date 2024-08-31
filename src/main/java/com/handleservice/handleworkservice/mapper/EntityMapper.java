package com.handleservice.handleworkservice.mapper;

public interface EntityMapper<T, U> {
    T toEntity(U dto);

    U toDTO(T entity);
}