package com.handleservice.handleworkservice.mapper.work;

import com.handleservice.handleworkservice.dto.work.UpdateWorkDTO;
import com.handleservice.handleworkservice.mapper.EntityMapper;
import com.handleservice.handleworkservice.model.Work;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UpdateWorkMapper extends EntityMapper<Work, UpdateWorkDTO> {
    UpdateWorkMapper INSTANCE = Mappers.getMapper(UpdateWorkMapper.class);

    @Override
    UpdateWorkDTO toDTO(Work entity);

    @Override
    Work toEntity(UpdateWorkDTO dto);
}
