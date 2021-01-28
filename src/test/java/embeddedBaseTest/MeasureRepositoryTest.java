package embeddedBaseTest;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import ru.isaykin.application.Application;
import ru.isaykin.application.model.Measure;
import ru.isaykin.application.model.Truck;
import ru.isaykin.application.repositories.MeasureRepository;
import ru.isaykin.application.repositories.TruckRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = Application.class)
@ComponentScan(value = "ru.isaykin.application")
@Sql(value = "/truckTableCleanTest.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@TestPropertySource(locations = "/application-test.properties")
public class MeasureRepositoryTest {

    @Autowired
    MeasureRepository measureRepository;

    @Autowired
    TruckRepository truckRepository;
    Truck truck;

    @BeforeEach
    public void setUp() {
        truck = new Truck("TestTruck", 16500, 5400, 400, 710);
        truck.setId(1L);

    }


    @Test
    public void createMeasureAndInsertIntoTable() {
        measureRepository.create(22000, 38000,
                Timestamp.valueOf(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)),
                4.5, 18000, 4, 20000, 1L,
                false, false, false, false);

        assertEquals(measureRepository.getAll().size(), 1, "Checking if truck was added to table and size if correct(1).");

    }

    @Test
    public void getById() {
        Measure expected = new Measure();
        expected.setCompleteWeight(10 * (5 * 400 + 3 * 710));
        expected.setFrontWeight((5 * 400) * 10 - 5400);
        expected.setRearWeight(3 * 710 * 10);
        expected.setCargoWeight((5 * 400 + 3 * 710) * 10 - 16500);
        expected.setCompleteOverloaded(false);
        expected.setFrontOverloaded(false);
        expected.setRearOverloaded(false);
        expected.setTruckId(truck.getId());
        expected.setOverloaded(false);
        expected.setRearBar(3);
        expected.setFrontBar(5);
        expected.setDateOfMeasure(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        measureRepository.save(expected);
        expected.setId(1L);

        Measure actual = measureRepository.getById(1L);

        assertEquals(actual, expected, "Checkin if measure was added to table in database.");
    }

    @Test
    public void deleteById() {
        Measure expected = new Measure();
        expected.setCompleteWeight(10 * (5 * 400 + 3 * 710));
        expected.setFrontWeight((5 * 400) * 10 - 5400);
        expected.setRearWeight(3 * 710 * 10);
        expected.setCargoWeight((5 * 400 + 3 * 710) * 10 - 16500);
        expected.setCompleteOverloaded(false);
        expected.setFrontOverloaded(false);
        expected.setRearOverloaded(false);
        expected.setTruckId(truck.getId());
        expected.setOverloaded(false);
        expected.setRearBar(3);
        expected.setFrontBar(5);
        expected.setDateOfMeasure(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        measureRepository.save(expected);
        expected.setId(1L);

        assertEquals(measureRepository.getAll().size(), 1, "Checking if truck was added to table and size if correct(1).");

        measureRepository.deleteById(1L);
        assertEquals(measureRepository.getAll().size(), 0, "Checking if truck was deleted from table and size if correct(0).");
    }

    @Test
    public void getByDateOfMeasure() {
        Measure expected = new Measure();
        expected.setCompleteWeight(10 * (5 * 400 + 3 * 710));
        expected.setFrontWeight((5 * 400) * 10 - 5400);
        expected.setRearWeight(3 * 710 * 10);
        expected.setCargoWeight((5 * 400 + 3 * 710) * 10 - 16500);
        expected.setCompleteOverloaded(false);
        expected.setFrontOverloaded(false);
        expected.setRearOverloaded(false);
        expected.setTruckId(truck.getId());
        expected.setOverloaded(false);
        expected.setRearBar(3);
        expected.setFrontBar(5);
        expected.setDateOfMeasure(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        measureRepository.save(expected);
        expected.setId(1L);

        Measure actual = measureRepository.getByDateOfMeasure(Timestamp.valueOf(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)));

        assertEquals(expected, actual);
    }


}
