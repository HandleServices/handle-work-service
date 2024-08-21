package com.handleservice.handleworkservice.unitTests.mapper;

import com.handleservice.handleworkservice.dto.work.WorkDTO;
import com.handleservice.handleworkservice.mapper.work.WorkMapper;
import com.handleservice.handleworkservice.model.Work;
import com.handleservice.handleworkservice.utils.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

public class WorkMapperTest extends BaseMapperTest<Work, WorkDTO> {

    public WorkMapperTest() {
        super(WorkMapper.INSTANCE);
    }

    @Override
    protected Pair<Work, WorkDTO> generatePair() {
        long id = 1L;
        UUID workId = UUID.fromString("883d15d2-d95f-4df4-809c-5ef5e6136f4d");
        BigDecimal value = BigDecimal.valueOf(100);
        String name = "trocar pneu";
        String description = null;
        boolean enabled = true;

        WorkDTO workDTO = new WorkDTO(id, name, description, value, enabled);

        Work work = new Work(id, workId, value, name, description, enabled);

        return new Pair<>(work, workDTO);
    }

    @Test
    @Override
    public void testToEntity() {
        Pair<Work, WorkDTO> matchingValues = generatePair();

        Work expectedWork = matchingValues.first();
        expectedWork.setWorkerId(null);

        WorkDTO expectedWorkDTO = matchingValues.second();

        Work result = mapper.toEntity(expectedWorkDTO);

        Assertions.assertEquals(expectedWork, result);
    }

    @Test
    @Override
    public void testToDto() {
        Pair<Work, WorkDTO> matchingValues = generatePair();

        Work expectedWork = matchingValues.first();
        WorkDTO expectedWorkDTO = matchingValues.second();

        WorkDTO result = mapper.toDTO(expectedWork);

        Assertions.assertEquals(expectedWorkDTO, result);
    }
}