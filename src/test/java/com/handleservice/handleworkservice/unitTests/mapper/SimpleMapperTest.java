package com.handleservice.handleworkservice.unitTests.mapper;


import com.handleservice.handleworkservice.mapper.SimpleEntityMapper;
import com.handleservice.handleworkservice.utils.Pair;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @SuppressWarnings("all")
    @Override
    protected Pair<TestModelClass, TestModelClass2> getMatchingValues() {
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
}
