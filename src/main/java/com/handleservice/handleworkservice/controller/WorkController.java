package com.handleservice.handleworkservice.controller;

import com.handleservice.handleworkservice.service.jwt.IJwtService;
import com.handleservice.handleworkservice.service.work.IWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/work")
public class WorkController {

    private final IJwtService _jwtService;
    private final IWorkService _workService;

    @Autowired
    public WorkController(IJwtService jwtService, IWorkService workService) {
        this._jwtService = jwtService;
        this._workService = workService;
    }
}
