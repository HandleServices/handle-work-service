package com.handleservice.handleworkservice.service.work;

import com.handleservice.handleworkservice.dto.work.CreateWorkDTO;
import com.handleservice.handleworkservice.dto.work.UpdateWorkDTO;
import com.handleservice.handleworkservice.dto.work.WorkDTO;
import com.handleservice.handleworkservice.mapper.EntityMapper;
import com.handleservice.handleworkservice.mapper.work.CreateWorkMapper;
import com.handleservice.handleworkservice.mapper.work.UpdateWorkMapper;
import com.handleservice.handleworkservice.mapper.work.WorkMapper;
import com.handleservice.handleworkservice.model.Work;
import com.handleservice.handleworkservice.repository.WorkRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WorkService implements IWorkService {

    private final WorkRepository _workRepository;

    private final EntityMapper<Work, WorkDTO> _workMapper = WorkMapper.INSTANCE;
    private final EntityMapper<Work, CreateWorkDTO> _createWorkMapper = CreateWorkMapper.INSTANCE;
    private final EntityMapper<Work, UpdateWorkDTO> _updateWorkMapper = UpdateWorkMapper.INSTANCE;

    @Autowired
    WorkService(WorkRepository workRepository) {
        this._workRepository = workRepository;
    }

    @Transactional
    @Override
    public Work findById(Long id, UUID workerId) {
        return _workRepository
                .findByIdAndWorkerId(id, workerId)
                .orElseThrow(() -> new EntityNotFoundException("Work not found"));
    }

    @Transactional
    @Override
    public List<Work> findAll(UUID workerId) {
        return _workRepository.findAllByWorkerId(workerId);
    }

    @Transactional
    @Override
    public Work save(UUID workerId, CreateWorkDTO createWorkDTO) {
        Work work = _createWorkMapper.toEntity(createWorkDTO);
        work.setWorkerId(workerId);
        return _workRepository.save(work);
    }

    @Transactional
    @Override
    public Work update(Long id, UUID workerId, UpdateWorkDTO updateWorkDTO) {
        if (!_workRepository.existsByIdAndWorkerId(id, workerId))
            throw new EntityNotFoundException("Work not found");

        Work workToUpdate = _updateWorkMapper.toEntity(updateWorkDTO);
        return _workRepository.updateWorkByIdAndWorkerId(id, workerId, workToUpdate);
    }

    @Transactional
    @Override
    public void delete(Long id, UUID workerId) {
        if (!_workRepository.existsByIdAndWorkerId(id,workerId))
            throw new EntityNotFoundException("Work not found");
        _workRepository.deleteById(id);
    }

}
