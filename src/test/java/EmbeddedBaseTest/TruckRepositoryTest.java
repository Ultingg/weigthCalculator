package EmbeddedBaseTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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


//@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Application.class
)

@AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:application-test.properties")
public class TruckRepositoryTest {


    @Autowired
    private TruckRepository truckRepository;
    @Autowired
    private TruckService truckService;

    @BeforeEach
    public void setDB() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2).addScript("schema.sql").addScript("data-test.sql").build();
    }

    @Test
    public void getter() {
        truckRepository.getAll();
    }

    @Test
    public void creation() {
        Truck truck = new Truck();
        truck.setTruckWeight(16000);
        truck.setFirstWheelWeight(6000);
        truck.setTruckNumber("G989AI/KA986");
        truck.setFrontPrice(550);
        truck.setRearPrice(400);

        truckRepository.save(truck);

        Truck actual = truckRepository.getById(1L);

        assertEquals(truck, actual);
    }

}
