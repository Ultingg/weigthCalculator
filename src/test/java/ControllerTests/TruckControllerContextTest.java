package ControllerTests;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.isaykin.application.Application;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
@ComponentScan(value = "ru.isaykin.application")
@TestPropertySource(locations = "/application-test.properties")
@Sql(value = "/dataInsertTruckAndMeasure.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/truckTableCleanTest.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class TruckControllerContextTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate jdbcTemplate;


}
