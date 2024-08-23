package com.handleservice.handleworkservice.service;

import com.handleservice.handleworkservice.dto.work.UpdateWorkDTO;
import com.handleservice.handleworkservice.dto.work.WorkDTO;
import com.handleservice.handleworkservice.model.Work;

import java.util.List;
import java.util.UUID;

public interface  IWorkService{

    Work findById(Long id, UUID workerId);

    Work save(UUID workerId, WorkDTO workDTO);

    List<Work> findAll(UUID workerId);

    Work update(Long id,UUID workerId, UpdateWorkDTO updateWorkDTO);

    void delete(Long id, UUID workerId);

}
