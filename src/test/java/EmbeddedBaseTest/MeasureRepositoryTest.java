package EmbeddedBaseTest;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import ru.isaykin.application.Application;
import ru.isaykin.application.model.Measure;
import ru.isaykin.application.repositories.MeasureRepository;
import ru.isaykin.application.repositories.TruckRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest(classes = Application.class)
@ComponentScan(value = "ru.isaykin.application")
@Sql(value = {"/measureTable-clean-test.sql", "/truckTable-clean-test.sql"},executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@TestPropertySource(locations = "/application-test.properties")
public class MeasureRepositoryTest {

    @Autowired
    MeasureRepository measureRepository;

    @Autowired
    TruckRepository truckRepository;

    @Test
    public void check() {
        truckRepository.create(6000,400,500,"G989AI/KA986",16000);
        measureRepository.create(22000,38000,
                Timestamp.valueOf(LocalDateTime.now()),
                4.5,18000,4,20000,1L,
                false,false,false,false);
        System.out.println(measureRepository.getAll().size());
    }

    @Test
    public void insertMeasureIntoTable() {
        measureRepository.create(22000,38000,
                Timestamp.valueOf(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)),
                4.5,18000,4,20000,1L,
                false,false,false,false);

        assertEquals(measureRepository.getAll().size(),1);

    }

    @Test
    public void getById() {
        measureRepository.create(22000,38000,
                Timestamp.valueOf(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)),
                4.5,18000,4,20000,1L,
                false,false,false,false);
        Measure measure = measureRepository.getById(1L);
        assertEquals(measure.getCompleteWeight(),38000);
    }





}
