package modelTests;

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
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class MeasureClassTests {

    private Truck truck;
    private Validator validator;


    @BeforeEach
    private void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        truck = new Truck("TestTruck", 16500, 5400, 400, 710);
        truck.setId(1L);
    }


    @Test
    public void overloadedTest_true() {
        Measure measureToTest = new Measure();
        measureToTest.calcWeights(truck, 5.25, 3.2);

        boolean actual = measureToTest.isOverloaded();
        assertTrue(actual, "Checking if overloaded is true.");
    }

    @Test
    public void OverloadedTest_false() {
        Measure measureToTest = new Measure();
        measureToTest.calcWeights(truck, 5, 3);

        boolean actual = measureToTest.isOverloaded();
        assertFalse(actual, "Checking if overloaded is false.");
    }

    @Test
    public void calcWeights_Valid_Success() {
        Measure excpected = new Measure();
        excpected.setCompleteWeight(10 * (5 * 400 + 3 * 710));
        excpected.setFrontWeight((5 * 400) * 10 - 5400);
        excpected.setRearWeight(3 * 710 * 10);
        excpected.setCargoWeight((5 * 400 + 3 * 710) * 10 - 16500);
        excpected.setCompleteOverloaded(false);
        excpected.setFrontOverloaded(false);
        excpected.setRearOverloaded(false);
        excpected.setTruckId(truck.getId());
        excpected.setOverloaded(false);
        excpected.setRearBar(3);
        excpected.setFrontBar(5);
        excpected.setDateOfMeasure(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

        Measure actual = new Measure();
        actual.calcWeights(truck, 5, 3);

        assertEquals(excpected, actual, "Checking if measures are the same.");
    }

    @Test
    public void MeasureValidationError_valid_NoErrors() {
        Measure measure = new Measure();

        measure.setFrontBar(1);
        measure.setRearBar(1);

        Set<ConstraintViolation<Measure>> violations = validator.validate(measure);
        assertTrue(violations.isEmpty(), "Checking if there are no validation errors");
    }

    @Test
    public void MeasureValidationError_noValid_Errors() {
        Measure measure = new Measure();

        measure.setFrontBar(0);
        measure.setRearBar(0);

        Set<ConstraintViolation<Measure>> violations = validator.validate(measure);
        assertFalse(violations.isEmpty(), "Checking if there are some validation errors");
    }

    @Test
    public void MeasureCalc_null_nullPointerException() {
        Measure actual = new Measure();
        Truck nullTruck = null;
        assertThrows(NullPointerException.class, () -> actual.calcWeights(nullTruck, 4, 5),
                "Checking if there is NullPointerException when Truck is null");
        assertThrows(NullPointerException.class, () -> actual.calcWeights(truck, 0, 5),
                "Checking if there is NullPointerException when frontBar is less then 1");
        assertThrows(NullPointerException.class, () -> actual.calcWeights(truck, 4, 0),
                "Checking if there is NullPointerException when rearBar is less then 1");
    }
}
