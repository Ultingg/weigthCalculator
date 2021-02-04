package modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.isaykin.application.model.Truck;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TruckTests {

    private Validator validator;
    private Truck truck;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        truck = new Truck("TestTruck", 16500, 5400, 400, 710);
        truck.setId(1L);
    }

    @Test
    public void truckNumberValidation_noValid_foundError() {
        truck.setTruckNumber("0");
        Set<ConstraintViolation<Truck>> violations = validator.validate(truck);
        assertFalse(violations.isEmpty(),
                "Checking if there are some validation errors");
    }

    @Test
    public void truckNumberValidation_emptyTruckNumber_foundError() {
        truck.setTruckNumber("");
        Set<ConstraintViolation<Truck>> violations = validator.validate(truck);
        assertFalse(violations.isEmpty(),
                "Checking if there are some validation errors");
    }

    @Test
    public void truckAllFieldsValidation_valid_noError() {
        Set<ConstraintViolation<Truck>> violations = validator.validate(truck);
        assertTrue(violations.isEmpty(),
                "Checking if there are no validation errors");
    }

    @Test
    public void frontPriceValidation_notValidFrontPrice_someError() {
        truck.setFrontPrice(0.0);
        Set<ConstraintViolation<Truck>> violations = validator.validate(truck);
        assertFalse(violations.isEmpty(),
                "Checking if there are some validation errors");
    }

    @Test
    public void frontPriceValidation_minusNotValidFrontPrice_someError() {
        truck.setFrontPrice(-1);
        Set<ConstraintViolation<Truck>> violations = validator.validate(truck);
        assertFalse(violations.isEmpty(),
                "Checking if there are some validation errors");
    }

    @Test
    public void frontRearValidation_notValidFrontPrice_someError() {
        truck.setRearPrice(0.0);
        Set<ConstraintViolation<Truck>> violations = validator.validate(truck);
        assertFalse(violations.isEmpty(),
                "Checking if there are some validation errors");
    }

    @Test
    public void frontRearValidation_minusNotValidFrontPrice_someError() {
        truck.setRearPrice(-1);
        Set<ConstraintViolation<Truck>> violations = validator.validate(truck);
        assertFalse(violations.isEmpty(),
                "Checking if there are some validation errors");
    }

    @Test
    public void frontFirstWheelWeightValidation_notValidFrontPrice_someError() {
        truck.setFirstWheelWeight(-1);
        Set<ConstraintViolation<Truck>> violations = validator.validate(truck);
        assertFalse(violations.isEmpty(),
                "Checking if there are some validation errors");
    }

    @Test
    public void frontTruckWeightValidation_notValidFrontPrice_someError() {
        truck.setTruckWeight(-1);
        Set<ConstraintViolation<Truck>> violations = validator.validate(truck);
        assertFalse(violations.isEmpty(),
                "Checking if there are some validation errors");
    }
}
