package com.handleservice.handleworkservice.unitTests.mapper;

import com.handleservice.handleworkservice.dto.work.WorkDTO;
import com.handleservice.handleworkservice.mapper.work.IWorkMapper;
import com.handleservice.handleworkservice.model.Work;
import com.handleservice.handleworkservice.utils.Pair;

import java.math.BigDecimal;
import java.util.UUID;

public class WorkMapperTest extends BaseMapperTest<Work, WorkDTO> {

    public WorkMapperTest() {
        super(IWorkMapper.INSTANCE);
    }

    @Override
    protected Pair<Work, WorkDTO> getMatchingValues() {
        long id = 1L;
        UUID workId = null;
        BigDecimal value = BigDecimal.valueOf(100);
        String name = "trocar pneu";
        String description = null;
        boolean enabled = true;

        WorkDTO workDTO = new WorkDTO(id, name, description, value, enabled);

        Work work = new Work(id, workId, value, name, description, enabled);

        return new Pair<>(work, workDTO);
    }
}