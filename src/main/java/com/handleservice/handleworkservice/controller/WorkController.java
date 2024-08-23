package com.handleservice.handleworkservice.controller;

import com.handleservice.handleworkservice.dto.work.CreateWorkDTO;
import com.handleservice.handleworkservice.dto.work.WorkDTO;
import com.handleservice.handleworkservice.mapper.work.WorkMapper;
import com.handleservice.handleworkservice.model.Work;
import com.handleservice.handleworkservice.service.jwt.IJwtService;
import com.handleservice.handleworkservice.service.work.IWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/work")
public class WorkController {

    private final IJwtService _jwtService;
    private final IWorkService _workService;
    private final WorkMapper _workMapper = WorkMapper.INSTANCE;

    @Autowired
    public WorkController(IJwtService jwtService, IWorkService workService) {
        this._jwtService = jwtService;
        this._workService = workService;
    }

    @GetMapping
    public List<WorkDTO> getAllWorks() {
        UUID workerId = UUID.fromString("3d92e6fa-964c-4fb7-b915-a5c897e02dcb");
        return _workService.findAll(workerId).stream().map(_workMapper::toDTO).toList();
    }

    @PostMapping
    public WorkDTO insert(@RequestBody CreateWorkDTO createWorkDTO){
        UUID workerId = UUID.fromString("3d92e6fa-964c-4fb7-b915-a5c897e02dcb");
        Work w =  _workService.insert(workerId, createWorkDTO);
        return _workMapper.toDTO(w);
    }
}
