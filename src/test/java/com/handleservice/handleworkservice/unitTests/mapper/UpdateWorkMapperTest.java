package com.handleservice.handleworkservice.unitTests.mapper;

import com.handleservice.handleworkservice.dto.work.UpdateWorkDTO;
import com.handleservice.handleworkservice.mapper.work.UpdateWorkMapper;
import com.handleservice.handleworkservice.model.Work;
import com.handleservice.handleworkservice.utils.Pair;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.UUID;

public class UpdateWorkMapperTest extends BaseMapperTest<Work, UpdateWorkDTO> {

    protected UpdateWorkMapperTest() {
        super(UpdateWorkMapper.INSTANCE);
    }

    @Override
    protected Pair<Work, UpdateWorkDTO> getMatchingValues() {
        Long id = null;
        UUID workId = null;
        BigDecimal value = BigDecimal.valueOf(100);
        String name = "trocar pneu";
        String description = null;
        LocalTime estimatedTime = LocalTime.of(12, 0,1);
        boolean enabled = true;

        UpdateWorkDTO updateWorkDTO = new UpdateWorkDTO(name, description, value, estimatedTime.toString(), enabled);

        Work work = new Work(id, workId, value, name, description, estimatedTime, enabled);

        return new Pair<>(work, updateWorkDTO);
    }
}
