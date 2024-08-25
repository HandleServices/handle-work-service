package com.handleservice.handleworkservice.config;

import com.handleservice.handleworkservice.mapper.work.CreateWorkMapper;
import com.handleservice.handleworkservice.mapper.work.UpdateWorkMapper;
import com.handleservice.handleworkservice.mapper.work.WorkMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    WorkMapper workMapper() {
        return WorkMapper.INSTANCE;
    }

    @Bean
    CreateWorkMapper createWorkMapper() {
        return CreateWorkMapper.INSTANCE;
    }

    @Bean
    UpdateWorkMapper updateWorkMapper() {
        return UpdateWorkMapper.INSTANCE;
    }
}
