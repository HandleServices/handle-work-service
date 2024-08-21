package com.handleservice.handleworkservice.unitTests.mapper;

import com.handleservice.handleworkservice.dto.work.UpdateWorkDTO;
import com.handleservice.handleworkservice.mapper.work.UpdateWorkMapper;
import com.handleservice.handleworkservice.model.Work;
import com.handleservice.handleworkservice.utils.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

public class UpdateWorkMapperTest extends BaseMapperTest<Work, UpdateWorkDTO> {

    protected UpdateWorkMapperTest() {
        super(UpdateWorkMapper.INSTANCE);
    }

    @Override
    protected Pair<Work, UpdateWorkDTO> generatePair() {
        long id = 1L;
        UUID workId = UUID.fromString("883d15d2-d95f-4df4-809c-5ef5e6136f4d");
        BigDecimal value = BigDecimal.valueOf(100);
        String name = "trocar pneu";
        String description = null;
        boolean enabled = true;

        UpdateWorkDTO updateWorkDTO = new UpdateWorkDTO( name, description, value, enabled);

        Work work = new Work(id, workId, value, name, description, enabled);

        return new Pair<>(work, updateWorkDTO );
    }

    @Test
    @Override
    public void testToEntity() {
        Pair<Work, UpdateWorkDTO> matchingValues = generatePair();

        Work expectedWork = matchingValues.first();
        expectedWork.setId(null);
        expectedWork.setWorkerId(null);

        UpdateWorkDTO expectedUpdateWorkDTO = matchingValues.second();

        Work result = mapper.toEntity(expectedUpdateWorkDTO);

        Assertions.assertEquals(expectedWork, result);
    }

    @Test
    @Override
    public void testToDto() {
        Pair<Work, UpdateWorkDTO> matchingValues = generatePair();

        Work expectedWork = matchingValues.first();
        UpdateWorkDTO expectedUpdateWorkDTO = matchingValues.second();

        UpdateWorkDTO result = mapper.toDTO(expectedWork);

        Assertions.assertEquals(expectedUpdateWorkDTO, result);
    }
}
