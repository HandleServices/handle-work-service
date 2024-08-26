package com.handleservice.handleworkservice.controller;

import com.handleservice.handleworkservice.dto.work.CreateWorkDTO;
import com.handleservice.handleworkservice.dto.work.UpdateWorkDTO;
import com.handleservice.handleworkservice.dto.work.WorkDTO;
import com.handleservice.handleworkservice.exception.custom.UnauthorizedException;
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
    public List<WorkDTO> getAllWorks(@RequestHeader(name = "Authorization", required = false) String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            throw new UnauthorizedException("Authorization header is missing or incorrect");
        }

        String jwtToken = token.replace("Bearer", "").trim();
        UUID workerId = UUID.fromString(_jwtService.extractAuthId(jwtToken));
        return _workService.findAll(workerId).stream().map(_workMapper::toDTO).toList();
    }

    @PostMapping
    @Operation(summary = "Insert a new work", description = "Insert a new work entity for a specific worker")
    public WorkDTO insert(@RequestHeader(name = "Authorization", required = false) String token,
                          @RequestBody CreateWorkDTO createWorkDTO) {
        if (token == null || !token.startsWith("Bearer ")) {
            throw new UnauthorizedException("Authorization header is missing or incorrect");
        }

        String jwtToken = token.replace("Bearer", "").trim();
        UUID workerId = UUID.fromString(_jwtService.extractAuthId(jwtToken));
        Work w = _workService.insert(workerId, createWorkDTO);
        return _workMapper.toDTO(w);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a work by id", description = "Get a work by id for a specific worker")
    public WorkDTO getWorkById(@RequestHeader(name = "Authorization", required = false) String token,
                               @PathVariable long id) {
        if (token == null || !token.startsWith("Bearer ")) {
            throw new UnauthorizedException("Authorization header is missing or incorrect");
        }

        String jwtToken = token.replace("Bearer", "").trim();
        UUID workerId = UUID.fromString(_jwtService.extractAuthId(jwtToken));
        Work w = _workService.findById(id, workerId);
        return _workMapper.toDTO(w);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update work", description = "Update a work entity for a specific worker")
    public WorkDTO updateWork(@RequestHeader(name = "Authorization", required = false) String token,
                              @PathVariable Long id,
                              @RequestBody UpdateWorkDTO updateWorkDTO) {
        if (token == null || !token.startsWith("Bearer ")) {
            throw new UnauthorizedException("Authorization header is missing or incorrect");
        }

        String jwtToken = token.replace("Bearer", "").trim();
        UUID workerId = UUID.fromString(_jwtService.extractAuthId(jwtToken));
        Work updatedWork = _workService.update(id, workerId, updateWorkDTO);
        return _workMapper.toDTO(updatedWork);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete work", description = "Delete a work by id for a specific worker")
    public void deleteWork(@RequestHeader(name = "Authorization", required = false) String token,
                              @PathVariable Long id) {
        if (token == null || !token.startsWith("Bearer ")) {
            throw new UnauthorizedException("Authorization header is missing or incorrect");
        }

        String jwtToken = token.replace("Bearer", "").trim();
        UUID workerId = UUID.fromString(_jwtService.extractAuthId(jwtToken));
        _workService.delete(id, workerId);
    }
}
