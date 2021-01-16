package EmbeddedBaseTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import ru.isaykin.application.Application;
import ru.isaykin.application.model.Truck;
import ru.isaykin.application.repositories.TruckRepository;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest(classes = Application.class)
@ComponentScan(value = "ru.isaykin.application")
@TestPropertySource(locations = "/application-test.properties")
public class TruckRepositoryTest {

    @Autowired
    private TruckRepository truckRepository;


    @Test
    public void creatNewTruck() {
        Truck truck = new Truck();
//        truck.setId(2L);
        truck.setTruckWeight(16000);
        truck.setFirstWheelWeight(6000);
        truck.setTruckNumber("G989AI/KA986");
        truck.setFrontPrice(550);
        truck.setRearPrice(400);
        truckRepository.create(6000,400,550,"G989AI/KA986",16000);
        Truck actual = truckRepository.getById(2L);


        Truck truck2 = new Truck();
//        truck2.setId(2L);
        truck2.setTruckWeight(16000);
        truck2.setFirstWheelWeight(6000);
        truck2.setTruckNumber("G989AI/KA986");
        truck2.setFrontPrice(550);
        truck2.setRearPrice(400);
        System.out.println(truck.hashCode());
        System.out.println(truck2.hashCode());

        assertEquals(truck,truck2);
    }

    @Test
    public void getter() {
        System.out.println(truckRepository.getAll());

        Truck truck = new Truck();
        truck.setTruckWeight(16000);
        truck.setFirstWheelWeight(6000);
        truck.setTruckNumber("G989AI/KA986");
        truck.setFrontPrice(550);
        truck.setRearPrice(400);

        truckRepository.create(6000,400,550,"G989AI/KA99996",16000);
        System.out.println(truckRepository.getAll());



        truckRepository.getById(1L);
//        truckRepository.save(truck);
        truckRepository.deleteById(1L);
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
