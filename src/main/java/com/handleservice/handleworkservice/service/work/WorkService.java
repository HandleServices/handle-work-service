package com.handleservice.handleworkservice.service.work;

import com.handleservice.handleworkservice.dto.work.CreateWorkDTO;
import com.handleservice.handleworkservice.dto.work.UpdateWorkDTO;
import com.handleservice.handleworkservice.exception.custom.DomainEntityNotFoundException;
import com.handleservice.handleworkservice.mapper.EntityMapper;
import com.handleservice.handleworkservice.mapper.work.CreateWorkMapper;
import com.handleservice.handleworkservice.mapper.work.UpdateWorkMapper;
import com.handleservice.handleworkservice.model.Work;
import com.handleservice.handleworkservice.repository.WorkRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WorkService implements IWorkService {

    private final WorkRepository _workRepository;
    private final EntityMapper<Work, CreateWorkDTO> _createWorkMapper;
    private final EntityMapper<Work, UpdateWorkDTO> _updateWorkMapper;

    @Autowired
    WorkService(
            WorkRepository workRepository,
            CreateWorkMapper createWorkMapper,
            UpdateWorkMapper updateWorkMapper
    ) {
        this._workRepository = workRepository;
        this._createWorkMapper = createWorkMapper;
        this._updateWorkMapper = updateWorkMapper;
    }

    @Transactional
    @Override
    public Work findById(Long id, UUID workerId) {
        return _workRepository
                .findByIdAndWorkerId(id, workerId)
                .orElseThrow(() -> new DomainEntityNotFoundException("Work not found"));
    }

    @Transactional
    @Override
    public List<Work> findAll(UUID workerId) {
        return _workRepository.findAllByWorkerId(workerId);
    }

    @Transactional
    @Override
    public Work insert(UUID workerId, CreateWorkDTO createWorkDTO) {
        Work work = _createWorkMapper.toEntity(createWorkDTO);
        work.setWorkerId(workerId);
        return _workRepository.save(work);
    }

    @Transactional
    @Override
    public Work update(Long id, UUID workerId, UpdateWorkDTO updateWorkDTO) {
        Work persistenceWork = _workRepository
                .findByIdAndWorkerId(id, workerId)
                .orElseThrow(() -> new DomainEntityNotFoundException("Work not found"));

        Work workToUpdate = _updateWorkMapper.toEntity(updateWorkDTO);

        persistenceWork.setName(workToUpdate.getName());
        persistenceWork.setDescription(workToUpdate.getDescription());
        persistenceWork.setValue(workToUpdate.getValue());
        persistenceWork.setEnable(workToUpdate.isEnable());

        return _workRepository.save(persistenceWork);

    }

    @Transactional
    @Override
    public void delete(Long id, UUID workerId) {
        if (!_workRepository.existsByIdAndWorkerId(id, workerId))
            throw new DomainEntityNotFoundException("Work not found");
        _workRepository.deleteById(id);
    }

}
