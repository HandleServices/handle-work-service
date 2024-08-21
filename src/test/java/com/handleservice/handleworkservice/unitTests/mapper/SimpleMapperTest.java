package com.handleservice.handleworkservice.unitTests.mapper;


import com.handleservice.handleworkservice.mapper.SimpleEntityMapper;
import com.handleservice.handleworkservice.utils.Pair;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Data
@NoArgsConstructor
class TestModelClass {
    private String name;
    private double value;
}

@Data
@NoArgsConstructor
class TestModelClass2 {
    private String name;
    private double value;
    private int value2;
}


public class SimpleMapperTest extends BaseMapperTest<TestModelClass, TestModelClass2> {

    protected SimpleMapperTest() {
        super(new SimpleEntityMapper<>(TestModelClass.class, TestModelClass2.class));
    }

    @Override
    protected Pair<TestModelClass, TestModelClass2> generatePair() {
        String name = "teste";
        double value = 1.0;
        TestModelClass testModelClass = new TestModelClass();
        testModelClass.setName(name);
        testModelClass.setValue(value);

        TestModelClass2 testModelClass2 = new TestModelClass2();
        testModelClass2.setName(name);
        testModelClass2.setValue(value);

        return new Pair<>(testModelClass, testModelClass2);
    }

    @Test
    @Override
    public void testToEntity() {
        Pair<TestModelClass, TestModelClass2> pair = generatePair();
        TestModelClass expectedTestModelClass = pair.first();
        TestModelClass2 expectedTestRecord = pair.second();

        TestModelClass result = mapper.toEntity(expectedTestRecord);

        Assertions.assertEquals(expectedTestModelClass, result);
    }

    @Test
    @Override
    public void testToDto() {
        Pair<TestModelClass, TestModelClass2> pair = generatePair();
        TestModelClass expectedTestModelClass = pair.first();
        TestModelClass2 expectedTestModelClass2 = pair.second();

        TestModelClass2 result = mapper.toDTO(expectedTestModelClass);

        Assertions.assertEquals(expectedTestModelClass2, result);

    }
}
