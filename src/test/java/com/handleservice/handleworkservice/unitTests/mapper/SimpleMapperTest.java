package com.handleservice.handleworkservice.unitTests.mapper;


import com.handleservice.handleworkservice.mapper.SimpleEntityMapper;
import com.handleservice.handleworkservice.utils.Pair;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// Example Entity class for testing
@Data
@NoArgsConstructor
class TestEntity {
    private String name;
    private double value;
}

// Example DTO class for testing
@Data
@NoArgsConstructor
class TestDTO {
    private String name;
    private double value;
    private int value2;
}


public class SimpleMapperTest extends BaseMapperTest<TestEntity, TestDTO> {

    protected SimpleMapperTest() {
        super(new SimpleEntityMapper<>(TestEntity.class, TestDTO.class));
    }

    @SuppressWarnings("all")
    @Override
    protected Pair<TestEntity, TestDTO> getMatchingValues() {
        String name = "teste";
        double value = 1.0;
        TestEntity testEntity = new TestEntity();
        testEntity.setName(name);
        testEntity.setValue(value);

        TestDTO testDTO = new TestDTO();
        testDTO.setName(name);
        testDTO.setValue(value);

        return new Pair<>(testEntity, testDTO);
    }

    // Example record class for testing
    record TestRecord(String name, int value) {
    }

    @Test
    void shouldThrowExceptionForRecordClass() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SimpleEntityMapper<>(TestRecord.class, TestDTO.class));

    }

    @Test
    void shouldNotThrowExceptionForNonRecordClass() {
        Assertions.assertDoesNotThrow(() -> {
            new SimpleEntityMapper<>(TestEntity.class, TestDTO.class);
        });
    }
}
