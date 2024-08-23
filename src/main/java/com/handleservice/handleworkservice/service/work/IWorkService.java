package com.handleservice.handleworkservice.service.work;

import com.handleservice.handleworkservice.dto.work.WorkDTO;

import java.util.UUID;

public interface IWorkService {

    WorkDTO findById(long id);

    WorkDTO save(UUID workerId, WorkDTO workDTO);
}
