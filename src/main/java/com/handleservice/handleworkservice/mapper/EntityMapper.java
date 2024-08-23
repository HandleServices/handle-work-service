package com.handleservice.handleworkservice.mapper;

public interface EntityMapper<T, U> extends EntityToDtoConverter<T, U>, DtoToEntityConverter<T, U> {
}