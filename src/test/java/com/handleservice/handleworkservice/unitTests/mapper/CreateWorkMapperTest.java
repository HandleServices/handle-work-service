package com.handleservice.handleworkservice.unitTests.mapper;

import com.handleservice.handleworkservice.dto.work.CreateWorkDTO;
import com.handleservice.handleworkservice.mapper.work.CreateWorkMapper;
import com.handleservice.handleworkservice.model.Work;
import com.handleservice.handleworkservice.utils.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

public class CreateWorkMapperTest extends BaseMapperTest<Work, CreateWorkDTO> {

    protected CreateWorkMapperTest() {
        super(CreateWorkMapper.INSTANCE);
    }

    @Override
    protected Pair<Work, CreateWorkDTO> generatePair() {
        long id = 1L;
        UUID workId = UUID.fromString("883d15d2-d95f-4df4-809c-5ef5e6136f4d");
        BigDecimal value = BigDecimal.valueOf(100);
        String name = "trocar pneu";
        String description = null;
        boolean enabled = true;

        Work work = new Work(id, workId, value, name, description, enabled);

        CreateWorkDTO  createWorkDTO = new CreateWorkDTO(name,description,value);

        return new Pair<>(work,createWorkDTO);
    }

    @Test
    @Override
    public void testToEntity() {
        Pair<Work, CreateWorkDTO> pair = generatePair();

        Work expectedWork = pair.first();
        expectedWork.setId(null);
        expectedWork.setWorkerId(null);

        CreateWorkDTO expectedCreateWorkDTO = pair.second();

        Work result = mapper.toEntity(expectedCreateWorkDTO);

        Assertions.assertEquals(expectedWork, result);
    }

    @Test
    @Override
    public void testToDto() {
        Pair<Work, CreateWorkDTO> pair = generatePair();

        Work expectedWork = pair.first();
        CreateWorkDTO expectedCreateWorkDTO = pair.second();

        CreateWorkDTO result = mapper.toDTO(expectedWork);

        Assertions.assertEquals(expectedCreateWorkDTO, result);
    }
}
