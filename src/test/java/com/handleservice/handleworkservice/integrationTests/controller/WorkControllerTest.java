package com.handleservice.handleworkservice.integrationTests.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.handleservice.handleworkservice.dto.work.CreateWorkDTO;
import com.handleservice.handleworkservice.dto.work.UpdateWorkDTO;
import com.handleservice.handleworkservice.dto.work.WorkDTO;
import com.handleservice.handleworkservice.model.Work;
import com.handleservice.handleworkservice.repository.WorkRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
public class WorkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WorkRepository workRepository;

    private final static String IMAGE_NAME = "postgres:16.4-alpine";

    @Container
    public static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(IMAGE_NAME)
            .withDatabaseName("test-db")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void configurePostgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
    }

    private UUID workerId;
    private LocalTime estimatedTime;
    private BigDecimal value;
    private String jwtToken;
    private String name;
    private String description;
    private final Work work = new Work();

    @BeforeAll
    public static void startContainer() { postgresContainer.start(); }

    @AfterAll
    static void stopContainers() {
        postgresContainer.stop();
    }

    @BeforeEach
    public void setUp() {
        workRepository.deleteAll();
        estimatedTime = LocalTime.of(11, 0);
        value = BigDecimal.ONE;
        jwtToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiMDAwNjFlMWQtYWRiMC00ZGJjLThhZGQtMWRhYmMyNmY5MjJiIiwiZXhwIjoxNzI0Mzg2MjExLjc4MjYxMTF9.x1SCgnNdubiLI0UZygUcsvP_r-LzTQGALIPY5UQ4qTc";
        workerId = UUID.fromString("00061e1d-adb0-4dbc-8add-1dabc26f922b");
        name = "New Work";
        description = "Work description";

        work.setValue(value);
        work.setWorkerId(workerId);
        work.setEnable(true);
        work.setName(name);
        work.setDescription(description);
        work.setEstimatedTime(estimatedTime);
    }

    @Test
    public void testInsert_shouldCreateNewWorkWithRightWorkerId() throws Exception {
        // Arrange
        CreateWorkDTO createWorkDTO = new CreateWorkDTO(
                name,
                description,
                estimatedTime.toString(),
                value
        );

        // Act
        mockMvc.perform(post("/work")
                        .header("Authorization", "Bearer "+jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createWorkDTO)))
                .andExpect(status().isOk());
        List<Work> works = workRepository.findAllByWorkerId(workerId);
        Work savedWork = works.getFirst();

        // Assert
        assertThat(works).hasSize(1);
        assertThat(savedWork.getName()).isEqualTo("New Work");
        assertThat(savedWork.getDescription()).isEqualTo("Work description");
        assertThat(savedWork.getEstimatedTime()).isEqualTo(estimatedTime);
        assertThat(savedWork.getValue().compareTo(value)).isZero();
        assertThat(savedWork.getWorkerId()).isEqualTo(workerId);
    }

    @Test
    public void testGetAllWorks_shouldReturnWorksForWorker() throws Exception {
        // Arrange
        workRepository.save(work);

        // Act
        MvcResult mvcResult = mockMvc.perform(get("/work")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();
        List<WorkDTO> works = objectMapper.readValue(jsonResponse,
                objectMapper.getTypeFactory().constructCollectionType(List.class, WorkDTO.class));


        // Assert
        assertThat(works).hasSize(1);
        WorkDTO fetchedWork = works.getFirst();
        assertThat(fetchedWork.name()).isEqualTo(name);
        assertThat(fetchedWork.id()).isEqualTo(work.getId());
    }

    @Test
    public void testGetWorkById_shouldReturnWorkForWorker() throws Exception {
        // Arrange
        workRepository.save(work);

        // Act
        MvcResult mvcResult = mockMvc.perform(get("/work/" + work.getId())
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();
        WorkDTO fetchedWork = objectMapper.readValue(jsonResponse, WorkDTO.class);

        // Assert
        assertThat(fetchedWork).isNotNull();
        assertThat(fetchedWork.name()).isEqualTo(name);
        assertThat(fetchedWork.id()).isEqualTo(work.getId());
    }

    @Test
    public void testUpdateWork_shouldUpdateExistingWork() throws Exception {
        // Arrange
        workRepository.save(work);
        UpdateWorkDTO updateWorkDTO = new UpdateWorkDTO("Updated Work", "Updated description", value, estimatedTime.toString(), false);

        // Act
        mockMvc.perform(put("/work/" + work.getId())
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateWorkDTO)))
                .andExpect(status().isOk());

        // Assert
        Work updatedWork = workRepository.findById(work.getId()).orElse(null);
        assertThat(updatedWork).isNotNull();
        assertThat(updatedWork.getName()).isEqualTo("Updated Work");
        assertThat(updatedWork.getDescription()).isEqualTo("Updated description");
        assertThat(updatedWork.isEnable()).isEqualTo(false);
    }

    @Test
    public void testDeleteWork_shouldRemoveWorkFromDatabase() throws Exception {
        // Arrange
        workRepository.save(work);

        // Act
        mockMvc.perform(delete("/work/" + work.getId())
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Assert
        Work deletedWork = workRepository.findById(work.getId()).orElse(null);
        assertThat(deletedWork).isNull();
    }



}
