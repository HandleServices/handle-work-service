package com.handleservice.handleworkservice.mapper.work;

import com.handleservice.handleworkservice.dto.work.CreateWorkDTO;
import com.handleservice.handleworkservice.mapper.EntityMapper;
import com.handleservice.handleworkservice.model.Work;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreateWorkMapper extends EntityMapper<Work, CreateWorkDTO> {
    CreateWorkMapper INSTANCE = Mappers.getMapper(CreateWorkMapper.class);
}
