package com.handleservice.handleworkservice.unitTests.controller;

import com.handleservice.handleworkservice.controller.WorkController;
import com.handleservice.handleworkservice.dto.work.CreateWorkDTO;
import com.handleservice.handleworkservice.dto.work.UpdateWorkDTO;
import com.handleservice.handleworkservice.dto.work.WorkDTO;
import com.handleservice.handleworkservice.exception.custom.DomainEntityNotFoundException;
import com.handleservice.handleworkservice.mapper.work.WorkMapper;
import com.handleservice.handleworkservice.model.Work;
import com.handleservice.handleworkservice.service.work.IWorkService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class WorkControllerTest {
    @InjectMocks
    private WorkController workController;

    @Mock
    private IWorkService workService;

    @Mock
    private WorkMapper workMapper;

    private Work work;
    private WorkDTO workDTO;
    private WorkDTO updatedWorkDTO;
    private UUID workerId;
    private CreateWorkDTO createWorkDTO;
    private UpdateWorkDTO updateWorkDTO;

    @BeforeEach
    public void setUp() {
        workerId = UUID.fromString("a3a389d9-909e-43dc-9df1-152ee945312c");
        BigDecimal value = new BigDecimal("123.45");
        String estimatedTime = LocalTime.of(12,0).toString();
        workDTO = new WorkDTO(1, "Work", "TestDescription", value, estimatedTime, true);
        createWorkDTO = new CreateWorkDTO("Work", "TestDescription", estimatedTime, value);
        updateWorkDTO = new UpdateWorkDTO("WorkUpdated", "DescriptionUpdated", value, estimatedTime, false);
        work = new Work();
        updatedWorkDTO = new WorkDTO(1, "WorkUpdated", "DescriptionUpdated", value, estimatedTime, false);
    }

    @Test
    void testGetAllWorks_shouldReturnAllWorks() {
        // Preparation
        when(workService.findAll(workerId)).thenReturn(List.of(work));
        when(workMapper.toDTO(any(Work.class))).thenReturn(workDTO);

        // Act
        List<WorkDTO> result = workController.getAllWorks(workerId);

        // Assert
        assertEquals(1, result.size());
        assertEquals(workDTO, result.getFirst());
    }

    @Test
    void testGetAllWorks_shouldCallWorkServiceAndMapperWithCorrectParams() {
        // Preparation
        when(workService.findAll(workerId)).thenReturn(List.of(work));
        when(workMapper.toDTO(work)).thenReturn(workDTO);

        // Act
        workController.getAllWorks(workerId);

        // Assert
        verify(workService).findAll(workerId);
        verify(workMapper).toDTO(work);
    }

    @Test
    void testInsertWork_shouldReturnTheInsertedWorkEntity() {
        // Preparation
        when(workService.insert(
                argThat(uuid -> uuid.equals(workerId)),
                argThat(workDto -> workDto.equals(createWorkDTO))
            )
        ).thenReturn(work);
        when(workMapper.toDTO(argThat(workReceive -> workReceive.equals(work)))).thenReturn(workDTO);

        // Act
        WorkDTO result = workController.insert(workerId, createWorkDTO);

        // Assert
        assertEquals(workDTO, result);
    }

    @Test
    void testInsertWork_shouldCallWorkServiceAndMapperWithCorrectParams() {
        // Preparation
        when(workService.insert(
                argThat(uuid -> uuid.equals(workerId)),
                argThat(workDto -> workDto.equals(createWorkDTO))
            )
        ).thenReturn(work);
        when(workMapper.toDTO(argThat(workReceive -> workReceive.equals(work)))).thenReturn(workDTO);

        // Act
        workController.insert(workerId, createWorkDTO);

        // Assert
        verify(workService).insert(workerId, createWorkDTO);
        verify(workMapper).toDTO(work);
    }

    @Test
    void testGetWorkById_shouldReturnAWorkWithTheRightId() {
        // Preparation
        when(workService.findById(
                argThat(id -> id == 1L),
                argThat(uuid -> uuid.equals(workerId))
            )
        ).thenReturn(work);

        when(workMapper.toDTO(
                argThat(workReceive -> workReceive.equals(work)))
        ).thenReturn(workDTO);

        // Act
        WorkDTO result = workController.getWorkById(workerId, 1L);

        // Assert
        assertEquals(workDTO, result);
    }

    @Test
    void testGetWorkById_shouldCallWorkServiceAndMapperWithCorrectParams() {
        // Preparation
        when(workService.findById(
                argThat(id -> id == 1L),
                argThat(uuid -> uuid.equals(workerId))
            )
        ).thenReturn(work);

        when(workMapper.toDTO(
                argThat(workReceive -> workReceive.equals(work)))
        ).thenReturn(workDTO);

        // Act
        workController.getWorkById(workerId, 1L);

        // Assert
        verify(workService).findById(1L, workerId);
        verify(workMapper).toDTO(work);
    }

    @Test
    void testUpdate_shouldReturnTheEntityUpdated() {
        // Preparation
        when(workService.update(
                argThat(id -> id == 1L),
                argThat(uuid -> uuid.equals(workerId)),
                argThat(toUpdate -> toUpdate.equals(updateWorkDTO))
            )
        ).thenReturn(work);

        when(workMapper.toDTO(argThat(workReceive -> workReceive.equals(work)))).thenReturn(updatedWorkDTO);

        // Act
        WorkDTO result = workController.updateWork(workerId, 1L, updateWorkDTO);

        // Assert
        assertEquals(updatedWorkDTO, result);
    }

    @Test
    void testUpdate_shouldCallWorkServiceAndMapperWithCorrectParams() {
        // Preparation
        when(workService.update(
                    argThat(id -> id == 1L),
                    argThat(uuid -> uuid.equals(workerId)),
                    argThat(toUpdate -> toUpdate.equals(updateWorkDTO))
            )
        ).thenReturn(work);

        when(workMapper.toDTO(argThat(workReceive -> workReceive.equals(work)))).thenReturn(updatedWorkDTO);

        // Act
        workController.updateWork(workerId, 1L, updateWorkDTO);

        // Assert
        verify(workService).update(1L, workerId, updateWorkDTO);
        verify(workMapper).toDTO(work);
    }



    @Test
    void testGetWorkById_givenNonExistentId_shouldThrowDomainEntityNotFoundException() {
        // Preparation
        long nonExistentId = -1L;
        when(workService.findById(nonExistentId,workerId)).thenThrow(DomainEntityNotFoundException.class);

        // Act and Assert
        assertThrows(DomainEntityNotFoundException.class, () -> workController.getWorkById(workerId, nonExistentId));
    }

}
