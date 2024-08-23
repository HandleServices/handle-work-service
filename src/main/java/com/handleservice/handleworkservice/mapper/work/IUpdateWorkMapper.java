package com.handleservice.handleworkservice.mapper.work;

import com.handleservice.handleworkservice.dto.work.UpdateWorkDTO;
import com.handleservice.handleworkservice.mapper.IEntityMapper;
import com.handleservice.handleworkservice.model.Work;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IUpdateWorkMapper extends IEntityMapper<Work, UpdateWorkDTO> {
    IUpdateWorkMapper INSTANCE = Mappers.getMapper(IUpdateWorkMapper.class);
}
