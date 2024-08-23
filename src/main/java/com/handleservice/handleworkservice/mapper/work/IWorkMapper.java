package com.handleservice.handleworkservice.mapper.work;

import com.handleservice.handleworkservice.dto.work.WorkDTO;
import com.handleservice.handleworkservice.mapper.IEntityMapper;
import com.handleservice.handleworkservice.model.Work;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IWorkMapper extends IEntityMapper<Work, WorkDTO> {
    IWorkMapper INSTANCE = Mappers.getMapper(IWorkMapper.class);
}
