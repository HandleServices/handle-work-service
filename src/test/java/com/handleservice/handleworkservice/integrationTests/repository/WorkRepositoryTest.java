package com.handleservice.handleworkservice.integrationTests.repository;

import com.handleservice.handleworkservice.infra.BaseDbTest;
import com.handleservice.handleworkservice.model.Work;
import com.handleservice.handleworkservice.repository.WorkRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
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
        BigDecimal value = BigDecimal.ONE;
        boolean enabled = true;

        work = new Work(id, workerId, value, name, description, enabled);
    }

    @Test
    public void testInsert(){
        //Given: A saved Work entity
        Work expectedWork = workRepository.save(work);

        //When: We retrieve the Work entity
        Work retreivedWork = workRepository.findById(expectedWork.getId()).orElse(null);

        //Then: The retrieved work should Match the Work entity
        Assertions.assertNotNull(retreivedWork);
        Assertions.assertEquals(expectedWork.getId(), retreivedWork.getId());
        Assertions.assertEquals(expectedWork.getWorkerId(), retreivedWork.getWorkerId());
        Assertions.assertEquals(expectedWork.getName(), retreivedWork.getName());
        Assertions.assertEquals(expectedWork.getDescription(), retreivedWork.getDescription());
        Assertions.assertEquals(expectedWork.isEnable(), retreivedWork.isEnable());
        Assertions.assertEquals(expectedWork.getValue(), retreivedWork.getValue());
        Assertions.assertNotNull(retreivedWork.getCreatedAt());
        Assertions.assertNotNull(retreivedWork.getUpdatedAt());
    }

    @Test
    public void testUpdate() {
        // Given: A saved Work entity
        Work expectedWork = workRepository.saveAndFlush(work);

        // When: We update the Work entity
        String nameToUpdate = "updated name";
        String descriptionToUpdate = "updated description";
        expectedWork.setName(nameToUpdate);
        expectedWork.setDescription(descriptionToUpdate);
        workRepository.saveAndFlush(expectedWork);

        // Then: The updated Work should match the expected values
        Work result = workRepository.findById(expectedWork.getId()).orElseThrow();
        Assertions.assertEquals(nameToUpdate, result.getName());
        Assertions.assertEquals(descriptionToUpdate, result.getDescription());
    }

    @Test
    public void testDelete() {
        // Given: A saved Work entity
        Work savedWork = workRepository.saveAndFlush(work);

        // When: We delete the Work entity
        workRepository.delete(savedWork);

        // And: We try to retrieve the Work entity
        Work retreivedWork = workRepository.findById(savedWork.getId()).orElse(null);

        // Then: The retrieved Work entity should not be found, therefore, should be null
        Assertions.assertNull(retreivedWork);
    }


    @Test
    public void testFindAllByWorkerId() {
        // Given: A saved Work entity
        Work insertedWork = workRepository.saveAndFlush(work);

        // When: We find Works by the inserted Work's worker ID
        List<Work> foundWorks = workRepository.findAllByWorkerId(insertedWork.getWorkerId());

        // Then: The found Works should contain the inserted Work
        Assertions.assertEquals(List.of(insertedWork), foundWorks);

        // And When: we find Works by a different worker ID
        UUID differentWorkerId = UUID.fromString("a3a389d9-909e-43dc-9df1-152ee945312c");
        List<Work> differentWorkerIdWorks = workRepository.findAllByWorkerId(differentWorkerId);

        // Then: The found Works should be empty
        Assertions.assertTrue(differentWorkerIdWorks.isEmpty());
    }

    @Test
    public void testFindByIdAndWorkerId() {
        // Given: A saved Work entity
        Work insertedWork = workRepository.saveAndFlush(work);

        // When: We find the Work by ID and worker ID
        Work resultFound = workRepository.findByIdAndWorkerId(insertedWork.getId(), insertedWork.getWorkerId()).orElse(null);

        // Then: The found Work should match the inserted Work
        Assertions.assertEquals(insertedWork, resultFound);

        // And When: We find the Work by ID and a different worker ID
        UUID differentWorkerId = UUID.fromString("a3a389d9-909e-43dc-9df1-152ee945312c");
        Work resultNotFound = workRepository.findByIdAndWorkerId(insertedWork.getId(), differentWorkerId).orElse(null);

        // Then: The Work should not be found
        Assertions.assertNull(resultNotFound);
    }

    @Test
    public void testExistsByIdAndWorkerId() {
        // Given: A saved Work entity
        Work insertedWork = workRepository.saveAndFlush(work);

        // When: We check if the Work exists by ID and worker ID
        boolean exists = workRepository.existsByIdAndWorkerId(insertedWork.getId(), insertedWork.getWorkerId());

        // Then: The Work should exist
        Assertions.assertTrue(exists);

        // When: We check if the Work exists by ID and a different worker ID
        UUID differentWorkerId = UUID.fromString("a3a389d9-909e-43dc-9df1-152ee945312c");
        boolean notExists = workRepository.existsByIdAndWorkerId(insertedWork.getId(), UUID.randomUUID());

        // Then: The Work should not exist
        Assertions.assertFalse(notExists);
    }
}
