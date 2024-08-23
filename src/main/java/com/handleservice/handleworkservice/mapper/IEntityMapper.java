package com.handleservice.handleworkservice.mapper;

public interface IEntityMapper<T, U> extends IEntityToDtoConverter<T, U>, IDtoToEntityConverter<T, U> {
}