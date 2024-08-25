package com.handleservice.handleworkservice.controller;

import com.handleservice.handleworkservice.dto.work.CreateWorkDTO;
import com.handleservice.handleworkservice.dto.work.WorkDTO;
import com.handleservice.handleworkservice.mapper.work.WorkMapper;
import com.handleservice.handleworkservice.model.Work;
import com.handleservice.handleworkservice.service.jwt.IJwtService;
import com.handleservice.handleworkservice.service.work.IWorkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/work")
@Tag(name = "Work", description = "API for managing work entities")
public class WorkController {

    private final IJwtService _jwtService;
    private final IWorkService _workService;
    private final WorkMapper _workMapper;

    @Autowired
    public WorkController(IJwtService jwtService, IWorkService workService, WorkMapper workMapper) {
        this._jwtService = jwtService;
        this._workService = workService;
        this._workMapper = workMapper;
    }

    @GetMapping
    @Operation(summary = "Get all works", description = "Retrieve all works for a specific worker")
    public List<WorkDTO> getAllWorks() {
        UUID workerId = UUID.fromString("3d92e6fa-964c-4fb7-b915-a5c897e02dcb");
        return _workService.findAll(workerId).stream().map(_workMapper::toDTO).toList();
    }

    @PostMapping
    @Operation(summary = "Insert a new work", description = "Insert a new work entity for a specific worker")
    public WorkDTO insert(@RequestBody CreateWorkDTO createWorkDTO) {
        UUID workerId = UUID.fromString("3d92e6fa-964c-4fb7-b915-a5c897e02dcb");
        Work w = _workService.insert(workerId, createWorkDTO);
        return _workMapper.toDTO(w);
    }
}
