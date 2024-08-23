package com.handleservice.handleworkservice.mapper;

@FunctionalInterface
public interface IEntityToDtoConverter<T, U> {
    U toDTO(T entity);
}
