package embeddedBaseTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import ru.isaykin.application.Application;
import ru.isaykin.application.model.Measure;
import ru.isaykin.application.model.Truck;
import ru.isaykin.application.repositories.MeasureRepository;
import ru.isaykin.application.repositories.TruckRepository;
import ru.isaykin.application.services.MeasureService;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(classes = Application.class)
@ComponentScan(value = "ru.isaykin.application")
@Sql(value="/data-insert-truck.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/truckTable-clean-test.sql","/measureTable-clean-test.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@TestPropertySource(locations = "/application-test.properties")
public class MeasureServiceTestEmbeddedBase {

    @Autowired
    private TruckRepository truckRepository;

    @Autowired
    private MeasureRepository measureRepository;

    private MeasureService measureService;

    @BeforeEach
    public void setUp() {
        measureService = new MeasureService(measureRepository, truckRepository);
    }

    @Test
    public void getById_validId_validMeasure() {
        Measure expected = Measure.builder()
                .completeWeight(41500)
                .cargoWeight(25000)
                .dateOfMeasure(LocalDateTime.of(2020,1, 1,8,0,0))
                .frontBar(5.2)
                .frontWeight(14800)
                .rearBar(3.45)
                .rearWeight(20700)
                .overloaded(false)
                .truckId(1L)
                .rearOverloaded(false)
                .frontOverloaded(false)
                .completeOverloaded(false)
                .id(1L)
                .build();

        Measure actual = measureService.getById(1L);

        assertEquals(expected, actual, "Checking if correct measure was gotten from table.");
    }

    @Test
    public void addMeasure_validMeasure_measureAddedToTable() {
        Truck newTruck = Truck.builder()
                .truckNumber("PER-895/PET-547")
                .truckWeight(15500)
                .firstWheelWeight(5500)
                .frontPrice(330)
                .rearPrice(550)
                .id(5L)
                .build();
        Measure expected = Measure.builder()
                .truckId(5L)
                .completeWeight(35750)
                .cargoWeight(20250)
                .dateOfMeasure(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .frontBar(5)
                .frontWeight(11000)
                .rearBar(3.5)
                .rearWeight(19250)
                .overloaded(false)
                .rearOverloaded(false)
                .frontOverloaded(false)
                .completeOverloaded(false)
                .id(5L)
                .build();
       Measure actual = measureService.addMeasure(newTruck,5,3.5);

       assertEquals(expected, actual, "Checking if correct measure was added to table.");
    }

    @Test
    public void deleteMeasureById_validId_MeasureDeleted() {
        measureService.deleteById(1L);
        List<Measure> measureList = measureRepository.getAll();

        assertEquals(measureList.size(),3, "Checking if measure was deleted from table.");
    }


}
