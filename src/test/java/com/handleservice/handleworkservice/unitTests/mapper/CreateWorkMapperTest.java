package com.handleservice.handleworkservice.unitTests.mapper;

import com.handleservice.handleworkservice.dto.work.CreateWorkDTO;
import com.handleservice.handleworkservice.infra.BaseMapperTest;
import com.handleservice.handleworkservice.mapper.work.CreateWorkMapper;
import com.handleservice.handleworkservice.model.Work;
import com.handleservice.handleworkservice.utils.Pair;

import java.math.BigDecimal;
import java.util.UUID;

public class CreateWorkMapperTest extends BaseMapperTest<Work, CreateWorkDTO> {

    protected CreateWorkMapperTest() {
        super(CreateWorkMapper.INSTANCE);
    }

    @Override
    protected Pair<Work, CreateWorkDTO> getMatchingValues() {
        Long id = null;
        UUID workId = null;
        BigDecimal value = BigDecimal.valueOf(100);
        String name = "trocar pneu";
        String description = null;
        boolean enabled = true;

        Work work = new Work(id, workId, value, name, description, enabled);

        CreateWorkDTO  createWorkDTO = new CreateWorkDTO(name,description,value);

        return new Pair<>(work,createWorkDTO);
    }
}
