package com.handleservice.handleworkservice.unitTests.controller;

import com.handleservice.handleworkservice.controller.WorkController;
import com.handleservice.handleworkservice.dto.work.WorkDTO;
import com.handleservice.handleworkservice.service.jwt.IJwtService;
import com.handleservice.handleworkservice.service.work.IWorkService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.UUID;

public class WorkControllerTest {
    @InjectMocks
    private WorkController workController;

    @Mock
    private IJwtService jwtService;

    @Mock
    private IWorkService workService;

    @Test
    void testGetAllWorks_shouldReturnAllWorks() {
        // Preparation
        String token = "Barear someValidToken";
        UUID workId = UUID.randomUUID();
        WorkDTO worDTO = new WorkDTO(1, "service", "TestDescription", 123.4, true);
    }
}
