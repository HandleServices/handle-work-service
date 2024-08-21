package com.handleservice.handleworkservice.integrationtest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJpaTest
@Testcontainers
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class WorkRepositoryTest {

    // Crie o container PostgreSQL com a mesma versão usada no Docker Compose
    private static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:16.4-alpine");

    @BeforeAll
    static void setUp() {
        // Inicie o container PostgreSQL
        postgresContainer.start();

        // Defina as propriedades do datasource com base no container iniciado
        System.setProperty("spring.datasource.url", postgresContainer.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgresContainer.getUsername());
        System.setProperty("spring.datasource.password", postgresContainer.getPassword());
    }

    @AfterAll
    static void stopContainers() {
        postgresContainer.stop();
    }

    @Test
    void createWork() {

    }
}
