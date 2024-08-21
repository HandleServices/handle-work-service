package com.handleservice.handleworkservice.mapper.work;
import com.handleservice.handleworkservice.dto.work.WorkDTO;
import com.handleservice.handleworkservice.mapper.EntityMapper;
import com.handleservice.handleworkservice.model.Work;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WorkMapper extends EntityMapper<Work, WorkDTO> {

    WorkMapper INSTANCE = Mappers.getMapper(WorkMapper.class);

    @Override
    Work toEntity(WorkDTO dto);

    @Override
    WorkDTO toDTO(Work entity);
}
