package measureTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.isaykin.application.model.Measure;
import ru.isaykin.application.model.Truck;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class MeasureClassTests {

    private Truck truck;
    private Validator validator;
    @BeforeEach
    private void SetUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        Set<Measure> measures = new HashSet();
        this.truck = new Truck("TestTruck", 16500, 5400, 400, 710);
        truck.setId(1L);
    }


    @Test
    public void OverloadedTest() {
        Measure measureToTest = new Measure();
        measureToTest.calcWeights(truck, 5.25, 3.2);
        System.out.println("полный вес: " + measureToTest.getCompleteWeight());
        System.out.println("вес на ведущую ось: " + measureToTest.getFrontWeight());
        System.out.println("вес на заднюю ось: " + measureToTest.getRearWeight());

        boolean actual = measureToTest.isOverloaded();
        assertTrue(actual);
    }

    @Test
    public void calcWeights_Valid_Success() {
        Measure actual = new Measure();
        actual.calcWeights(truck, 5,3);

        Measure excpected = new Measure();
        excpected.setCompleteWeight(10*(5*400+3*710));
        excpected.setFrontWeight((5*400)*10-5400);
        excpected.setRearWeight(3*710*10);
        excpected.setCargoWeight((5*400+3*710)*10-16500);
        excpected.setCompleteOverloaded(false);
        excpected.setFrontOverloaded(false);
        excpected.setRearOverloaded(false);
        excpected.setTruckId(truck.getId());
        excpected.setOverloaded(false);
        excpected.setRearBar(3);
        excpected.setFrontBar(5);
        excpected.setDateOfMeasure(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

        assertEquals(excpected,actual);
    }

    @Test
    public void MeasureValidationError() {

        Measure measure = new Measure( );

        measure.setFrontBar(-1);
        measure.setRearBar(0);
        System.out.println("Did it!");
        System.out.println(measure.getFrontBar());
        Set<ConstraintViolation<Measure>> violations = validator.validate(measure);
        assertFalse(violations.isEmpty());

    }
}
