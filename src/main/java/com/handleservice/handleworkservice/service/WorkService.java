package com.handleservice.handleworkservice.service;

import com.handleservice.handleworkservice.dto.work.WorkDTO;
import com.handleservice.handleworkservice.mapper.EntityMapper;
import com.handleservice.handleworkservice.mapper.work.WorkMapper;
import com.handleservice.handleworkservice.model.Work;
import com.handleservice.handleworkservice.repository.WorkRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WorkService {

    private final WorkRepository workRepository;

    private final EntityMapper<Work, WorkDTO> workEntityMapper = WorkMapper.INSTANCE;

    WorkService(WorkRepository workRepository) {
        this.workRepository = workRepository;
    }

    public WorkDTO findById(long id) {
        Work work = workRepository.findById(id).orElse(null);
        return workEntityMapper.toDTO(work);
    }

    public WorkDTO save(UUID WorkerId, WorkDTO workDTO) {
        Work work = workEntityMapper.toEntity(workDTO);
        work.setWorkerId(WorkerId);
        work = workRepository.save(work);
        workRepository.flush();
        return workEntityMapper.toDTO(work);
    }


}
