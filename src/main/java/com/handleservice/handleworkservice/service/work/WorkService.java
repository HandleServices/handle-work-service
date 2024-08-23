package com.handleservice.handleworkservice.service.work;

import com.handleservice.handleworkservice.dto.work.WorkDTO;
import com.handleservice.handleworkservice.mapper.IEntityMapper;
import com.handleservice.handleworkservice.mapper.work.IWorkMapper;
import com.handleservice.handleworkservice.model.Work;
import com.handleservice.handleworkservice.repository.IWorkRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WorkService implements IWorkService {

    private final IWorkRepository _workRepository;

    private final IEntityMapper<Work, WorkDTO> _workEntityMapper;

    WorkService(IWorkRepository workRepository) {
        this._workRepository = workRepository;
        this._workEntityMapper = IWorkMapper.INSTANCE;;
    }

    @Override
    public WorkDTO findById(long id) {
        Work work = _workRepository.findById(id).orElse(null);
        return _workEntityMapper.toDTO(work);
    }

    @Override
    public WorkDTO save(UUID workerId, WorkDTO workDTO) {
        Work work = _workEntityMapper.toEntity(workDTO);
        work.setWorkerId(workerId);
        work = _workRepository.save(work);
        _workRepository.flush();
        return _workEntityMapper.toDTO(work);
    }
}