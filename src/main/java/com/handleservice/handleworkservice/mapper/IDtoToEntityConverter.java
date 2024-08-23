package com.handleservice.handleworkservice.mapper;

@FunctionalInterface
public interface IDtoToEntityConverter<T, U> {
    T toEntity(U dto);
}
