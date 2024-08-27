package com.handleservice.handleworkservice.controller;

import com.handleservice.handleworkservice.config.annotation.WorkerId;
import com.handleservice.handleworkservice.dto.work.CreateWorkDTO;
import com.handleservice.handleworkservice.dto.work.UpdateWorkDTO;
import com.handleservice.handleworkservice.dto.work.WorkDTO;
import com.handleservice.handleworkservice.mapper.work.WorkMapper;
import com.handleservice.handleworkservice.model.Work;
import com.handleservice.handleworkservice.service.work.IWorkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/work")
@Tag(name = "Work", description = "API for managing work entities")
public class WorkController {

    private final IWorkService _workService;
    private final WorkMapper _workMapper;

    @Autowired
    public WorkController(IWorkService workService, WorkMapper workMapper) {
        this._workService = workService;
        this._workMapper = workMapper;
    }

    @GetMapping
    @Operation(summary = "Get all works", description = "Retrieve all works for a specific worker")
    public List<WorkDTO> getAllWorks(
            @Parameter(hidden = true) @WorkerId UUID workerId
    ) {
        return _workService.findAll(workerId).stream().map(_workMapper::toDTO).toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a work by id", description = "Get a work by id for a specific worker")
    public WorkDTO getWorkById(
            @Parameter(hidden = true) @WorkerId UUID workerId,
            @PathVariable long id
    ) {
        Work w = _workService.findById(id, workerId);
        return _workMapper.toDTO(w);
    }

    @PostMapping
    @Operation(summary = "Insert a new work", description = "Insert a new work entity for a specific worker")
    public WorkDTO insert(
            @Parameter(hidden = true) @WorkerId UUID workerId,
            @RequestBody CreateWorkDTO createWorkDTO
    ) {
        Work w = _workService.insert(workerId, createWorkDTO);
        return _workMapper.toDTO(w);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update work", description = "Update a work entity for a specific worker")
    public WorkDTO updateWork(
            @Parameter(hidden = true) @WorkerId UUID workerId,
            @PathVariable Long id,
            @RequestBody UpdateWorkDTO updateWorkDTO
    ) {
        Work updatedWork = _workService.update(id, workerId, updateWorkDTO);
        return _workMapper.toDTO(updatedWork);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete work", description = "Delete a work by id for a specific worker")
    public void deleteWork(
            @Parameter(hidden = true) @WorkerId UUID workerId,
            @PathVariable Long id
    ) {
        _workService.delete(id, workerId);
    }
}
