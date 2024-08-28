package com.handleservice.handleworkservice.unitTests.controller;

import com.handleservice.handleworkservice.controller.WorkController;
import com.handleservice.handleworkservice.dto.work.WorkDTO;
import com.handleservice.handleworkservice.exception.custom.DomainEntityNotFoundException;
import com.handleservice.handleworkservice.mapper.work.WorkMapper;
import com.handleservice.handleworkservice.model.Work;
import com.handleservice.handleworkservice.service.work.IWorkService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkControllerTest {
    @InjectMocks
    private WorkController workController;

    @Mock
    private IWorkService workService;

    @Mock
    private WorkMapper workMapper;

    private Work work;
    private WorkDTO workDTO;
    private UUID workerId;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        workerId = UUID.fromString("a3a389d9-909e-43dc-9df1-152ee945312c");
        BigDecimal value = new BigDecimal("123.45");
        LocalTime estimatedTime = LocalTime.of(12,0);
        workDTO = new WorkDTO(1, "Work", "TestDescription", value, estimatedTime.toString(), true);
        work = new Work();

    }

    @Test
    void testGetAllWorks_shouldReturnAllWorks() {
        when(workService.findAll(workerId)).thenReturn(Collections.singletonList(new Work()));
        when(workMapper.toDTO(any(Work.class))).thenReturn(workDTO);

        // Act
        List<WorkDTO> result = workController.getAllWorks(workerId);

        // Assert
        assertEquals(1, result.size());
        assertEquals(workDTO, result.getFirst());
    }

    @Test
    void testGetAllWorks_shouldCallWorkServiceAndMapperWithCorrectParams() {
        when(workService.findAll(workerId)).thenReturn(List.of(work));
        when(workMapper.toDTO(work)).thenReturn(workDTO);

        // Act
        List<WorkDTO> result = workController.getAllWorks(workerId);

        // Assert
        verify(workService).findAll(workerId);
        verify(workMapper).toDTO(work);
    }

    @Test
    void testGetWorkById_givenNonExistentId_shouldThrowDomainEntityNotFoundException() {
        // Preparation
        long nonExistentId = -1L;
        when(workService.findById(nonExistentId,workerId)).thenThrow(DomainEntityNotFoundException.class);

        // Assert
        assertThrows(DomainEntityNotFoundException.class, () -> workController.getWorkById(workerId, nonExistentId));
    }

}
