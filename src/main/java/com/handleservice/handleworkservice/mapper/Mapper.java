package com.handleservice.handleworkservice.mapper;

import java.util.List;

public interface Mapper<T, U> {
    U toDTO(T entity);
    T toEntity(U dto);
    List<U> toDTOList(List<T> entities);
    List<T> toEntityList(List<U> dtos);

}