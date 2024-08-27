package com.handleservice.handleworkservice.integrationTests.repository;

import com.handleservice.handleworkservice.infra.BaseDbTest;
import com.handleservice.handleworkservice.model.Work;
import com.handleservice.handleworkservice.repository.WorkRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class WorkRepositoryTest extends BaseDbTest {

    @Autowired
    private WorkRepository workRepository;

    private Work work;

    @BeforeEach
    public void setUp() {
        workRepository.deleteAll();

        Long id = 1L;
        UUID workerId = UUID.fromString("3d92e6fa-964c-4fb7-b915-a5c897e02dcb");
        String name = "name";
        String description = "description";
        LocalTime estimatedTime = LocalTime.of(11, 0);
        BigDecimal value = BigDecimal.ONE;
        boolean enabled = true;

        work = new Work(id, workerId, value, name, description, estimatedTime, enabled);
    }

    @Test
    public void testInsert_givenWork_whenRetrievedById_thenWorkReturnedShouldMatchWorkSaved() {
        // Act
        Work expectedWork = workRepository.save(work);

        // Asset
        Work retreivedWork = workRepository.findById(expectedWork.getId()).orElse(null);
        Assertions.assertNotNull(retreivedWork);
        Assertions.assertEquals(expectedWork.getId(), retreivedWork.getId());
        Assertions.assertEquals(expectedWork.getWorkerId(), retreivedWork.getWorkerId());
        Assertions.assertEquals(expectedWork.getName(), retreivedWork.getName());
        Assertions.assertEquals(expectedWork.getDescription(), retreivedWork.getDescription());
        Assertions.assertEquals(expectedWork.isEnable(), retreivedWork.isEnable());
        Assertions.assertEquals(expectedWork.getValue(), retreivedWork.getValue());
        Assertions.assertEquals(expectedWork.getEstimatedTime(), retreivedWork.getEstimatedTime());
        Assertions.assertNotNull(retreivedWork.getCreatedAt());
        Assertions.assertNotNull(retreivedWork.getUpdatedAt());
    }

    @Test
    public void testUpdate_shouldChangeWorkAttributes() {
        // Preparation
        Work expectedWork = workRepository.saveAndFlush(work);
        String nameToUpdate = "updated name";
        String descriptionToUpdate = "updated description";
        LocalTime estimatedTimeToUpdate = LocalTime.of(12,1);
        BigDecimal valueToUpdate = BigDecimal.ONE;
        boolean enabledToUpdate = false;

        // Act
        expectedWork.setName(nameToUpdate);
        expectedWork.setDescription(descriptionToUpdate);
        expectedWork.setValue(valueToUpdate);
        expectedWork.setEnable(enabledToUpdate);
        expectedWork.setEstimatedTime(estimatedTimeToUpdate);
        workRepository.saveAndFlush(expectedWork);

        // Assert
        Work result = workRepository.findById(expectedWork.getId()).orElseThrow();
        Assertions.assertEquals(nameToUpdate, result.getName());
        Assertions.assertEquals(descriptionToUpdate, result.getDescription());
        Assertions.assertEquals(valueToUpdate, result.getValue());
        Assertions.assertEquals(enabledToUpdate, result.isEnable());
        Assertions.assertEquals(estimatedTimeToUpdate, result.getEstimatedTime());
    }

    @Test
    public void testDelete_shouldRemoveWorkEntity() {
        // Preparation
        Work savedWork = workRepository.saveAndFlush(work);

        // Act
        workRepository.delete(savedWork);
        Work retreivedWork = workRepository.findById(savedWork.getId()).orElse(null);

        // Assert
        Assertions.assertNull(retreivedWork);
    }


    @Test
    public void testFindAllByWorkerId_shouldFindWork() {
        // Preparation
        Work insertedWork = workRepository.saveAndFlush(work);

        // Act
        List<Work> foundWorks = workRepository.findAllByWorkerId(insertedWork.getWorkerId());

        // Assert
        Assertions.assertEquals(List.of(insertedWork), foundWorks);
    }


    @Test
    public void testFindAllByWorkerId_shouldNotFindWork() {
        // Preparation
        workRepository.saveAndFlush(work);
        UUID differentWorkerId = UUID.fromString("a3a389d9-909e-43dc-9df1-152ee945312c");

        // Act
        List<Work> foundWorks = workRepository.findAllByWorkerId(differentWorkerId);

        //Assert
        Assertions.assertTrue(foundWorks.isEmpty());
    }

    @Test
    public void testFindByIdAndWorkerId_givenIdAndWorkerId_shouldFindWork() {
        // Preparation
        Work insertedWork = workRepository.saveAndFlush(work);

        // Act
        Work resultFound = workRepository.findByIdAndWorkerId(insertedWork.getId(), insertedWork.getWorkerId()).orElse(null);

        // Assert
        Assertions.assertEquals(insertedWork, resultFound);
    }

    @Test
    public void testFindByIdAndWorkerId_givenIdAndDifferentWorkerId_shouldNotFindWork() {
        // Preparation
        Work insertedWork = workRepository.saveAndFlush(work);
        UUID differentWorkerId = UUID.fromString("a3a389d9-909e-43dc-9df1-152ee945312c");

        // Act
        Work foundWork = workRepository.findByIdAndWorkerId(insertedWork.getId(), differentWorkerId).orElse(null);

        // Assert
        Assertions.assertNull(foundWork);
    }

    @Test
    public void testExistsByIdAndWorkerId_givenIdAndWorkerId_shouldReturnTrue() {
        // Preparation
        Work insertedWork = workRepository.saveAndFlush(work);

        // Act
        boolean exists = workRepository.existsByIdAndWorkerId(insertedWork.getId(), insertedWork.getWorkerId());

        // Assert
        Assertions.assertTrue(exists);
    }

    @Test
    public void testExistsByIdAndWorkerId_givenIdAndDifferentWorkerId_shouldReturnFalse() {
        // Preparation
        Work insertedWork = workRepository.saveAndFlush(work);
        UUID differentWorkerId = UUID.fromString("a3a389d9-909e-43dc-9df1-152ee945312c");

        // Act
        boolean exists = workRepository.existsByIdAndWorkerId(insertedWork.getId(), differentWorkerId);

        // Assert
        Assertions.assertFalse(exists);
    }
}
