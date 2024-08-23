package com.handleservice.handleworkservice.mapper;

@FunctionalInterface
public interface EntityToDtoConverter<T, U> {
    U toDTO(T entity);
}
