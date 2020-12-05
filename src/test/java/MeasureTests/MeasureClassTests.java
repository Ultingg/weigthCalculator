package MeasureTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.isaykin.application.model.Measure;
import ru.isaykin.application.model.Truck;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MeasureClassTests {

    private Truck truck;

    @BeforeEach
    private void SetUp(){
        this.truck = new Truck("TestTruck", 16500, 5400,400,710);

    }



    @Test
    public void OverloadedTest() {
        Measure measureToTest = new Measure();
        measureToTest.calcWeights(truck, 5.25,3.2);
        System.out.println("полный вес: " + measureToTest.getCompleteWeight());
        System.out.println("вес на ведущую ось: " + measureToTest.getFrontWeight());
        System.out.println("вес на заднюю ось: " + measureToTest.getRearWeight());

        boolean actual = measureToTest.isOverloaded();
        assertTrue(actual);
    }

}
