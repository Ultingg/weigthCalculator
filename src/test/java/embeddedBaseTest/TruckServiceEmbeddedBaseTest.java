package embeddedBaseTest;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.isaykin.application.Application;
import ru.isaykin.application.model.Measure;
import ru.isaykin.application.model.Truck;
import ru.isaykin.application.repositories.MeasureRepository;
import ru.isaykin.application.repositories.TruckRepository;
import ru.isaykin.application.services.TruckService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
//@ComponentScan(value = "ru.isaykin.application")
@Sql(value = "/dataInsertTruckAndMeasure.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/truckTableCleanTest.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@TestPropertySource(locations = "/application-test.properties")
public class TruckServiceEmbeddedBaseTest {

    @Autowired
    private TruckRepository truckRepository;

    @Autowired
    private MeasureRepository measureRepository;

    private TruckService truckService;

    @BeforeEach
    public void setUp() {
        truckService = new TruckService(truckRepository, measureRepository);
    }

    @Test
    public void deleteTruckById_validId_TruckDeleted() {
        truckService.deleteById(1L);

        List<Measure> measureList = measureRepository.getAll();

        assertTrue(measureList.isEmpty(), "Checking if all measures of Truck with id = 1 are deleted.");
        assertNull(truckRepository.getById(1L), "Checking if truck was deleted.");
    }

    @Test
    public void updateTruckById_validTruckId_TruckUpdated() {
        Truck newTruck = Truck.builder()
                .truckNumber("PER-895/PET-547")
                .truckWeight(15500)
                .firstWheelWeight(5500)
                .frontPrice(330)
                .rearPrice(550)
                .build();
        truckService.updateById(2L, newTruck);
        newTruck.setId(2L);

        Truck actual = truckService.getTruck(2L);

        assertEquals(newTruck, actual, "Checking if truck was updated correctly.");
    }

    @Test
    public void addTruck_valid_TruckAdded() {
        Truck newTruck = Truck.builder()
                .truckNumber("PER-895/PET-547")
                .truckWeight(15500)
                .firstWheelWeight(5500)
                .frontPrice(330)
                .rearPrice(550)
                .build();
        truckService.addTruck(newTruck);
        newTruck.setId(5L);

        Truck actual = truckService.getTruck(5L);

        assertEquals(newTruck, actual, "Checking if truck was added to table correctly.");
    }

    @Test
    public void getAll_valid_getAll() {
        List<Truck> truckList = truckService.getAll();

        assertEquals(truckList.size(), 4, "Checking if there are 4 trucks in table.");
    }

    @Test
    public void getTruck_valid_getValidTruck() {
        Truck expected = Truck.builder()
                .truckNumber("EGW-542/DNM-698")
                .truckWeight(16500)
                .firstWheelWeight(6000)
                .frontPrice(400)
                .rearPrice(600)
                .id(1L)
                .build();
        Truck actual = truckService.getTruck(1L);

        assertEquals(expected, actual);
    }

}
