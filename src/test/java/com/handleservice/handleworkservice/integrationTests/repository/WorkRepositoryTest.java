package com.handleservice.handleworkservice.integrationTests.repository;

import com.handleservice.handleworkservice.infra.BaseDbTest;
import com.handleservice.handleworkservice.model.Work;
import com.handleservice.handleworkservice.repository.WorkRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.UUID;

public class WorkRepositoryTest extends BaseDbTest {

    @Autowired
    private WorkRepository workRepository;

    @Test
    public void update(){
//        Work workToInsert = new Work();
//        workToInsert.setWorkerId(UUID.fromString("3d92e6fa-964c-4fb7-b915-a5c897e02dcb"));
//        workToInsert.setValue(BigDecimal.valueOf(100));
//        workToInsert.setName("placeholder1");
//        workToInsert.setDescription("description");
//
//        Work work = workRepository.saveAndFlush(workToInsert);
//
//        Work workToUpdate = new Work();
//        workToUpdate.setId(work.getId());
//        workToUpdate.setWorkerId(work.getWorkerId());
//        workToUpdate.setValue(work.getValue());
//        workToUpdate.setDescription(null);
//        workToUpdate.setName("placeholder2");
//
//
//        workRepository.updateWorkByIdAndWorkerId(work.getId(), work.getWorkerId(),workToUpdate);
//
//        workRepository.flush();
//
//
//        System.out.println(work);
//
//        Assertions.assertNotEquals(work,workToInsert);
    }
}
