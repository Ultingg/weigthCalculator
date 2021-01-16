package EmbeddedBaseTest;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import ru.isaykin.application.Application;
import ru.isaykin.application.model.Truck;
import ru.isaykin.application.repositories.TruckRepository;
import ru.isaykin.application.services.TruckService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest(classes = Application.class)
@ComponentScan(value = "ru.isaykin.application")
@TestPropertySource(locations = "/application-test.properties")
public class TruckRepositoryTest {

    @Autowired
    private TruckRepository truckRepository;
    @Autowired
    private TruckService truckService;


    @Test
    public void getter() {
        System.out.println(truckRepository.getAll());

        Truck truck = new Truck();
        truck.setTruckWeight(16000);
        truck.setFirstWheelWeight(6000);
        truck.setTruckNumber("G989AI/KA986");
        truck.setFrontPrice(550);
        truck.setRearPrice(400);

        truckRepository.create(6000,400,550,"G989AI/KA986",16000);
        System.out.println(truckRepository.getAll());

        truckRepository.getById(1L);
        System.out.println(truckRepository.getAll());
    }

    @Test
    public void creation() {
        Truck truck = new Truck();
        truck.setTruckWeight(16000);
        truck.setFirstWheelWeight(6000);
        truck.setTruckNumber("G989AI/KA986");
        truck.setFrontPrice(550);
        truck.setRearPrice(400);
        truckRepository.create(6000,400,550,"G989AI/KA986",16000);


        Truck actual = truckRepository.getById(1L);

        assertEquals(1, actual.getId());
    }

}
