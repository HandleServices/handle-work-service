package com.handleservice.handleworkservice.unitTests.service;

import com.handleservice.handleworkservice.dto.work.CreateWorkDTO;
import com.handleservice.handleworkservice.dto.work.UpdateWorkDTO;
import com.handleservice.handleworkservice.exception.custom.DomainEntityNotFoundException;
import com.handleservice.handleworkservice.mapper.work.CreateWorkMapper;
import com.handleservice.handleworkservice.mapper.work.UpdateWorkMapper;
import com.handleservice.handleworkservice.model.Work;
import com.handleservice.handleworkservice.repository.WorkRepository;
import com.handleservice.handleworkservice.service.work.WorkService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WorkServiceTest {
    @InjectMocks
    private WorkService workService;

    @Mock
    private WorkRepository workRepository;

    @Mock
    private CreateWorkMapper createWorkMapper;

    @Mock
    private UpdateWorkMapper updateWorkMapper;

    private UUID workerId;
    private Work work1;
    private CreateWorkDTO createWorkDTO;
    private UpdateWorkDTO updateWorkDTO;

    @BeforeEach
    void setUp() {
        workerId = UUID.fromString("a3a389d9-909e-43dc-9df1-152ee945312c");
        LocalTime estimatedTime = LocalTime.of(12,0);

        work1 = new Work();
        work1.setId(1L);
        work1.setWorkerId(workerId);
        work1.setName("Work 1");
        work1.setDescription("Description 1");
        work1.setValue(new BigDecimal("100.00"));
        work1.setEnable(true);
        work1.setEstimatedTime(LocalTime.of(10, 0));

        createWorkDTO = new CreateWorkDTO(
                "Work",
                "Test Description",
                estimatedTime.toString(),
                new BigDecimal("123.45")
        );

        updateWorkDTO = new UpdateWorkDTO(
                "Updated Work",
                "Updated Description",
                new BigDecimal("150.00"),
                estimatedTime.toString(),
                false
        );
    }

    @Test
    void testFindById_shouldReturnRightWork() {
        // Arrange
        when(workRepository.findByIdAndWorkerId(work1.getId(), workerId)).thenReturn(Optional.of(work1));

        // Act
        Work result = workService.findById(work1.getId(), workerId);

        // Assert
        assertEquals(work1, result);
    }

    @Test
    void testFindById_shouldCallRepositoryWithRightParameters() {
        // Arrange
        when(workRepository.findByIdAndWorkerId(work1.getId(), workerId)).thenReturn(Optional.of(work1));

        // Act
        Work result = workService.findById(work1.getId(), workerId);

        // Assert
        verify(workRepository).findByIdAndWorkerId(work1.getId(), workerId);
    }

    @Test
    void testFindById_givenNonExistentIdOrWorkerId_shouldThrowException() {
        // Arrange
        when(workRepository.findByIdAndWorkerId(work1.getId(), workerId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(DomainEntityNotFoundException.class, () -> workService.findById(work1.getId(), workerId));
    }

    @Test
    void testFindAll_givenWorkerId_shouldReturnAllWorks() {
        // Arrange
        Work work2 = new Work();
        work2.setId(2L);
        work2.setWorkerId(workerId);
        work2.setName("Work 2");
        work2.setDescription("Description 2");
        work2.setValue(new BigDecimal("200.00"));
        work2.setEnable(true);
        work2.setEstimatedTime(LocalTime.of(11, 0));

        List<Work> works = List.of(work1, work2);

        when(workRepository.findAllByWorkerId(workerId)).thenReturn(works);

        // Act
        List<Work> result = workService.findAll(workerId);

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(work1));
        assertTrue(result.contains(work2));
        verify(workRepository).findAllByWorkerId(workerId);
    }

    @Test
    void testInsert_shouldSaveAndReturnRightWork() {
        // Arrange
        when(createWorkMapper.toEntity(createWorkDTO)).thenReturn(work1);
        when(workRepository.save(work1)).thenReturn(work1);

        // Act
        Work result = workService.insert(workerId, createWorkDTO);

        // Assert
        assertEquals(work1, result);
        verify(workRepository).save(work1);
        verify(createWorkMapper).toEntity(createWorkDTO);
    }

    @Test
    void testUpdate_shouldUpdateAndReturnRightWork() {
        // Arrange
        Work updatedWork = new Work();
        updatedWork.setId(1L);
        updatedWork.setWorkerId(work1.getWorkerId());
        updatedWork.setName(updateWorkDTO.name());
        updatedWork.setDescription(updateWorkDTO.description());
        updatedWork.setValue(updateWorkDTO.value());
        updatedWork.setEnable(updateWorkDTO.enable());
        updatedWork.setEstimatedTime(work1.getEstimatedTime());

        when(workRepository.findByIdAndWorkerId(work1.getId(), workerId)).thenReturn(Optional.of(work1));
        when(updateWorkMapper.toEntity(updateWorkDTO)).thenReturn(updatedWork);
        when(workRepository.save(updatedWork)).thenReturn(updatedWork);

        // Act
        Work result = workService.update(work1.getId(), workerId, updateWorkDTO);

        // Assert
        assertEquals(result, updatedWork);
        verify(workRepository).findByIdAndWorkerId(work1.getId(), workerId);
        verify(workRepository).save(work1);
        verify(updateWorkMapper).toEntity(updateWorkDTO);
    }

    @Test
    void testUpdate_givenNonExistentIdOrWorkerId_shouldThrowException() {
        // Arrange
        when(workRepository.findByIdAndWorkerId(work1.getId(), workerId)).thenReturn(Optional.empty());

        // Assert & Act
        assertThrows(DomainEntityNotFoundException.class, () -> workService.update(work1.getId(), workerId, updateWorkDTO));
    }

    @Test
    void testDelete_shouldDeleteWorkWhenFound() {
        // Arrange
        when(workRepository.existsByIdAndWorkerId(work1.getId(), workerId)).thenReturn(true);

        // Act
        workService.delete(work1.getId(), workerId);

        // Assert
        verify(workRepository).deleteById(work1.getId());
    }

    @Test
    void testDelete_shouldThrowExceptionWhenNotFound() {
        // Arrange
        when(workRepository.existsByIdAndWorkerId(work1.getId(), workerId)).thenReturn(false);

        // Act & Assert
        assertThrows(DomainEntityNotFoundException.class, () -> workService.delete(work1.getId(), workerId));
        verify(workRepository, never()).deleteById(work1.getId());
    }

}
