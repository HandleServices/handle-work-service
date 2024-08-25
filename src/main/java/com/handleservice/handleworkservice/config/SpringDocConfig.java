package com.handleservice.handleworkservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI springDocOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Handle Work Service")
                        .description("Service to manage works")
                        .version("0")
                );
    }
}
