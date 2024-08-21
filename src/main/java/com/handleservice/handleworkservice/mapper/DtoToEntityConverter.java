package com.handleservice.handleworkservice.mapper;

@FunctionalInterface
public interface DtoToEntityConverter<T, U> {
    T toEntity(U dto);
}
