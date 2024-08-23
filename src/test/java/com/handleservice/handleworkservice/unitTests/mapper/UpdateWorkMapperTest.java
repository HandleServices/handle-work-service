package com.handleservice.handleworkservice.unitTests.mapper;

import com.handleservice.handleworkservice.dto.work.UpdateWorkDTO;
import com.handleservice.handleworkservice.infra.BaseMapperTest;
import com.handleservice.handleworkservice.mapper.work.UpdateWorkMapper;
import com.handleservice.handleworkservice.model.Work;
import com.handleservice.handleworkservice.utils.Pair;

import java.math.BigDecimal;
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
        boolean enabled = true;

        UpdateWorkDTO updateWorkDTO = new UpdateWorkDTO( name, description, value, enabled);

        Work work = new Work(id, workId, value, name, description, enabled);

        return new Pair<>(work, updateWorkDTO );
    }
}
