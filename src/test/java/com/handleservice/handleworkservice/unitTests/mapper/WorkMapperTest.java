package com.handleservice.handleworkservice.unitTests.mapper;

import com.handleservice.handleworkservice.dto.work.WorkDTO;
import com.handleservice.handleworkservice.mapper.work.WorkMapper;
import com.handleservice.handleworkservice.model.Work;
import com.handleservice.handleworkservice.utils.Pair;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.UUID;

public class WorkMapperTest extends BaseMapperTest<Work, WorkDTO> {

    public WorkMapperTest() {
        super(WorkMapper.INSTANCE);
    }

    @Override
    protected Pair<Work, WorkDTO> getMatchingValues() {
        long id = 1L;
        UUID workId = null;
        BigDecimal value = BigDecimal.valueOf(100);
        String name = "trocar pneu";
        String description = null;
        LocalTime estimatedTime = LocalTime.of(12,0,1);
        boolean enabled = true;

        WorkDTO workDTO = new WorkDTO(id, name, description, value, estimatedTime.toString(), enabled);

        Work work = new Work(id, workId, value, name, description, estimatedTime, enabled);

        return new Pair<>(work, workDTO);
    }
}