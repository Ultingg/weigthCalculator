package embeddedBaseTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import ru.isaykin.application.Application;
import ru.isaykin.application.model.Truck;
import ru.isaykin.application.repositories.TruckRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest(classes = Application.class)
@ComponentScan(value = "ru.isaykin.application")
@Sql(value = "/truckTable-clean-test.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@TestPropertySource(locations = "/application-test.properties")
public class TruckRepositoryTest {

    @Autowired
    private TruckRepository truckRepository;

    @Test
    public void creatNewTruck() {
        Truck truck = new Truck("G989AI/KA986", 16000, 6000, 400, 550);
        truckRepository.create(6000, 400, 550, "G989AI/KA986", 16000);
        Truck actual = truckRepository.getById(1L);
        truck.setId(1L);
        assertEquals(truck, actual
                , "Checking if truck was added to table of database");
        assertEquals("G989AI/KA986", actual.getTruckNumber()
                , "Checking if truck was added to table of database by it's TruckNumber.");

    }

    @Test
    public void getAllList() {
        truckRepository.create(6000, 400, 550, "G989AI/KA986", 16000);
        List<Truck> truckList = truckRepository.getAll();

        assertEquals("G989AI/KA986", truckList.get(0).getTruckNumber()
                , "Checking if truck was added to table of database.");
        assertEquals(truckList.size(), 1, "Checking size of table of database, it must be 1.");
    }

    @Test
    public void deleteById() {
        truckRepository.create(6000, 400, 550, "G989AI/KA986", 16000);
        List<Truck> truckList = truckRepository.getAll();

        assertEquals("G989AI/KA986", truckList.get(0).getTruckNumber()
                , "Checking if truck was added to table of database.");
        assertEquals(truckList.size(), 1
                , "Checking size of table of database, it must be 1.");

        truckRepository.deleteById(1L);
        truckList = truckRepository.getAll();

        assertEquals(truckList.size(), 0
                , "Checkin if Tuck was deleted from table of database.");
    }

    @Test
    public void saveTruck() {
        truckRepository.create(5500, 450, 70, "P345TO98", 9500);
        Truck truck = Truck.builder()
                .id(1L)
                .truckNumber("P345TO98/AT878578")
                .truckWeight(16500)
                .frontPrice(450)
                .rearPrice(700)
                .firstWheelWeight(5500)
                .build();
        truckRepository.save(truck);
        Truck truckToCheck = truckRepository.getById(1L);


        assertEquals(truckToCheck, truck
                , "Checking if truck was updated correctly.");
        assertEquals(truckToCheck.getTruckNumber()
                , "P345TO98/AT878578", "Checking if TruckNumber was updated correctly.");
    }

}
