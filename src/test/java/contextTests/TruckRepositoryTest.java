package contextTests;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import ru.isaykin.application.Application;
import ru.isaykin.application.model.Truck;
import ru.isaykin.application.repositories.TruckRepository;
import ru.isaykin.application.services.TruckService;

import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(
//        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
//        classes = Application.class
//)
//
//@AutoConfigureTestDatabase
//@TestPropertySource(locations = "classpath:application-test.properties")
public class TruckRepositoryTest {

    @Autowired
    private TruckRepository truckRepository;
    @Autowired
    private TruckService truckService;

    @BeforeEach
            public void setDB() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2).addScript("schema.sql").addScript("data.sql").build();
    }

    @Test
    public void finsById() {
        Truck truck = new Truck();
        truck.setId(1L);
        truck.setTruckNumber("TOR909/LOKI23");
        truck.setFrontPrice(400);
        truck.setRearPrice(570);
        truck.setFirstWheelWeight(5600);
        truck.setTruckWeight(15800);
        //truckRepository.create(5600, 400, 570, "TOR909/LOKI23", 15800);
        truckService.addTruck(truck);
        Truck actual = truckRepository.getById(1L);

        assertEquals(truck, actual);

    }
}
