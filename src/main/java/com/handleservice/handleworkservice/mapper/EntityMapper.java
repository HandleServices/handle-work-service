package com.handleservice.handleworkservice.mapper;

public interface EntityMapper<T, U> {
    U toDTO(T entity);
    T toEntity(U dto);
}