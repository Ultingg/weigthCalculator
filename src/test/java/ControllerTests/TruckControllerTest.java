package ControllerTests;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import ru.isaykin.application.Application;
import ru.isaykin.application.controllers.MeasureController;
import ru.isaykin.application.controllers.TruckController;
import ru.isaykin.application.model.Truck;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(classes = Application.class)
@ComponentScan(value = "ru.isaykin.application")
@Sql(value = "/dataInsertTruckAndMeasure.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/truckTableCleanTest.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class TruckControllerTest {

    @Autowired
    private TruckController truckController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MeasureController measureController;


    @Test
    public void contextLoad() {
        assertNotNull(truckController);
    }

    @Test
    public void getTruckList_200OKTruckList() throws Exception {
        mockMvc.perform(get("/trucks/list")).andExpect(status().isOk())
                .andExpect(view().name("truckList"));
    }


    @Test
    public void creating_200OkNewTruckView() throws Exception {
        mockMvc.perform(post("/trucks/creation"))
                .andExpect(view().name("newTruck"))
                .andDo(print())
                .andReturn();
    }

    @Test
    public void getTruckInfo_validId_200OKTruckCard() throws Exception {
        Truck expectedTruck = Truck.builder()
                .truckNumber("EGW-542/DNM-698")
                .id(1L)
                .truckWeight(16500)
                .firstWheelWeight(6000)
                .frontPrice(400)
                .rearPrice(600)
                .build();
        mockMvc.perform(get("/trucks/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("truckCard"))
                .andExpect(model().attribute("truck", expectedTruck))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteTruck_validId_deleteTruck() throws Exception {
        mockMvc.perform(delete("/trucks/{id}", "1"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/trucks/list"));
    }


}

