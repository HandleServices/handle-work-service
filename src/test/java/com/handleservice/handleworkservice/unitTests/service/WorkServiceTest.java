package com.handleservice.handleworkservice.unitTests.service;

import com.handleservice.handleworkservice.dto.work.CreateWorkDTO;
import com.handleservice.handleworkservice.dto.work.UpdateWorkDTO;
import com.handleservice.handleworkservice.exception.custom.DomainEntityNotFoundException;
import com.handleservice.handleworkservice.mapper.EntityMapper;
import com.handleservice.handleworkservice.mapper.work.WorkMapper;
import com.handleservice.handleworkservice.model.Work;
import com.handleservice.handleworkservice.repository.WorkRepository;
import com.handleservice.handleworkservice.service.work.IWorkService;
import com.handleservice.handleworkservice.service.work.WorkService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WorkServiceTest {
    @InjectMocks
    private WorkService workService;

    @Mock
    private WorkRepository workRepository;

    @Mock
    private EntityMapper<Work, CreateWorkDTO> createWorkMapper;

    @Mock
    private EntityMapper<Work, UpdateWorkDTO> updateWorkMapper;

    private UUID workerId;
    private Work work;
    private CreateWorkDTO createWorkDTO;
    private UpdateWorkDTO updateWorkDTO;

    @BeforeEach
    void setUp() {
        workerId = UUID.fromString("a3a389d9-909e-43dc-9df1-152ee945312c");
        LocalTime estimatedTime = LocalTime.of(12,0);

        work = new Work();
        work.setId(1L);
        work.setWorkerId(workerId);
        work.setName("Work");
        work.setDescription("Test Description");
        work.setValue(new BigDecimal("123.45"));
        work.setEnable(true);
        work.setEstimatedTime(estimatedTime);

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
        when(workRepository.findByIdAndWorkerId(work.getId(), workerId)).thenReturn(Optional.of(work));

        // Act
        Work result = workService.findById(work.getId(), workerId);

        // Assert
        assertEquals(work, result);
    }

    @Test
    void testFindById_shouldCallRepositoryWithRightParameters() {
        // Arrange
        when(workRepository.findByIdAndWorkerId(work.getId(), workerId)).thenReturn(Optional.of(work));

        // Act
        Work result = workService.findById(work.getId(), workerId);

        // Assert
        verify(workRepository).findByIdAndWorkerId(work.getId(), workerId);
    }

    @Test
    void testFindById_shouldThrowExceptionWhenNotFound() {
        // Arrange
        when(workRepository.findByIdAndWorkerId(work.getId(), workerId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(DomainEntityNotFoundException.class, () -> workService.findById(work.getId(), workerId));
    }

//    @Test
//    void testInsert_shouldSaveAndReturnWork() {
//        // Arrange
//        when(createWorkMapper.toEntity(createWorkDTO)).thenReturn(work);
//        when(workRepository.save(work)).thenReturn(work);
//
//        // Act
//        Work result = workService.insert(workerId, createWorkDTO);
//
//        // Assert
//        assertEquals(work, result);
//    }


}
