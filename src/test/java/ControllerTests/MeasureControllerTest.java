package ControllerTests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import ru.isaykin.application.Application;
import ru.isaykin.application.DTO.MeasureDTO;
import ru.isaykin.application.controllers.MeasureController;
import ru.isaykin.application.mappers.MeasureMapper;
import ru.isaykin.application.model.MarkerOfFilter;
import ru.isaykin.application.model.Measure;
import ru.isaykin.application.model.Truck;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(classes = Application.class)
@ComponentScan(value = "ru.isaykin.application")
@TestPropertySource(locations = "/application-test.properties")
@Sql(value = "/dataInsertTruckAndMeasure.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/truckTableCleanTest.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class MeasureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MeasureController measureController;

    @Mock
    private Model model;

    private Measure measureGlobal;


    @BeforeEach
    public void setUp() {
        measureGlobal = Measure.builder().id(1L).cargoWeight(25000).completeWeight(41500)
                .dateOfMeasure(LocalDateTime.parse("2020-01-01T08:00:00")).frontBar(5.2).frontWeight(14800)
                .rearBar(3.45).rearWeight(20700).overloaded(false).truckId(1L).frontOverloaded(false)
                .rearOverloaded(false).completeOverloaded(false).build();
    }


    @Test
    public void contextLoad() {
        assertNotNull(measureController);
    }



    @Test
    public void getMarker_valid_validMarker() {
        MarkerOfFilter expected = new MarkerOfFilter(false);

        MarkerOfFilter actual = measureController.getMarker();

        assertEquals(expected, actual);
    }

    @Test
    public void getMeasureList_validList() {
        Truck truck = Truck.builder()
                .id(1L).firstWheelWeight(6000).frontPrice(400).rearPrice(600)
                .truckWeight(16500).truckNumber("EGW-542/DNM-698").build();
        Truck truck2 = Truck.builder()
                .id(2L).firstWheelWeight(6100).frontPrice(380).rearPrice(650)
                .truckWeight(16800).truckNumber("ROQ-675/DNO-777").build();
        Truck truck3 = Truck.builder()
                .id(3L).firstWheelWeight(5400).frontPrice(350).rearPrice(700)
                .truckWeight(16300).truckNumber("LRA-547/MOT-193").build();
        Truck truck4 = Truck.builder()
                .id(4L).firstWheelWeight(5800).frontPrice(400).rearPrice(500)
                .truckWeight(15800).truckNumber("FKX-875/DKL-686").build();
        List<Truck> expected = Arrays.asList(truck, truck2, truck3, truck4);

        List<Truck> actual = measureController.getTruckListUtil();
        assertEquals(expected, actual);
    }

    @Test
    public void getMeasureDTOList_valid_MeasureDTOList() {
        Truck truck = Truck.builder()
                .id(1L).firstWheelWeight(6000).frontPrice(400).rearPrice(600)
                .truckWeight(16500).truckNumber("EGW-542/DNM-698").build();
        Measure measure = Measure.builder().id(1L).cargoWeight(25000).completeWeight(41500)
                .dateOfMeasure(LocalDateTime.parse("2020-01-01T08:00:00")).frontBar(5.2).frontWeight(14800)
                .rearBar(3.45).rearWeight(20700).overloaded(false).truckId(1L).frontOverloaded(false)
                .rearOverloaded(false).completeOverloaded(false).build();
        MeasureDTO measureDTO = MeasureMapper.INSTANCE.fromMeasure(measure, truck);
        Measure measure2 = Measure.builder().id(2L).cargoWeight(27100).completeWeight(43500)
                .dateOfMeasure(LocalDateTime.parse("2020-01-03T08:00:00")).frontBar(5.2).frontWeight(14800)
                .rearBar(3.8).rearWeight(22800).overloaded(false).truckId(1L).frontOverloaded(false)
                .rearOverloaded(false).completeOverloaded(false).build();
        MeasureDTO measureDTO2 = MeasureMapper.INSTANCE.fromMeasure(measure2, truck);
        Measure measure3 = Measure.builder().id(3L).cargoWeight(27100).completeWeight(41600)
                .dateOfMeasure(LocalDateTime.parse("2020-01-04T08:00:00")).frontBar(5.5).frontWeight(16000)
                .rearBar(3.6).rearWeight(21600).overloaded(true).truckId(1L).frontOverloaded(true)
                .rearOverloaded(false).completeOverloaded(false).build();
        MeasureDTO measureDTO3 = MeasureMapper.INSTANCE.fromMeasure(measure3, truck);
        Measure measure4 = Measure.builder().id(4L).cargoWeight(29000).completeWeight(45500)
                .dateOfMeasure(LocalDateTime.parse("2020-01-05T08:00:00")).frontBar(5.5).frontWeight(16000)
                .rearBar(4).rearWeight(24000).overloaded(true).truckId(1L).frontOverloaded(true)
                .rearOverloaded(true).completeOverloaded(true).build();
        MeasureDTO measureDTO4 = MeasureMapper.INSTANCE.fromMeasure(measure4, truck);
        List<MeasureDTO> expected = Arrays.asList(measureDTO, measureDTO2, measureDTO3, measureDTO4);

        List<MeasureDTO> actual = measureController.getMeasureDTOListUtil();

        assertEquals(expected, actual);
    }


    @Test
    public void measureRecipeTest_noErrorsInValidation_calculationView() {

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        String expected = measureController.measureRecipe(measureGlobal, bindingResult, model);

        assertEquals("measureRecipe", expected);
    }



}
