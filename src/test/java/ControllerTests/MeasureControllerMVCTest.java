package ControllerTests;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.isaykin.application.Application;
import ru.isaykin.application.controllers.MeasureController;
import ru.isaykin.application.model.MarkerOfFilter;
import ru.isaykin.application.model.Measure;
import ru.isaykin.application.repositories.MeasureRepository;
import ru.isaykin.application.repositories.TruckRepository;
import ru.isaykin.application.services.TruckService;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class)
public class MeasureControllerMVCTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MeasureRepository measureRepository;

    @MockBean
    private TruckRepository truckRepository;

    @Autowired
    private TruckService truckService;

    @Autowired
    private MeasureController measureController;


    @Test
    public void mainPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(view().name("main"))
                .andExpect(content().string(containsString("Создать новый ТС")));

    }

    @Test
    public void measurePanelView() throws Exception {
        mockMvc.perform(get("/measure"))
                .andExpect(view().name("measureList")).
                andExpect(MockMvcResultMatchers.content().string(containsString("Отфильтровать измерения по номеру ТС")));

    }

    @Test
    public void creation_viewCalculation() throws Exception {
        mockMvc.perform(get("/trucks/measure/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("calculation"))
                .andDo(print())
                .andReturn();
    }

    @Test
    public void measureRecipeTest_errorsInValidation_calculationView() throws Exception {
        mockMvc.perform(post("/trucks/measure/created"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("measure"))
                .andExpect(view().name("calculation"))
                .andDo(print())
                .andReturn();
    }


    @Test
    public void getListOfMeasureByTruckId_validID_ListOfMeasureByTruckId() throws Exception {
        MarkerOfFilter expected = new MarkerOfFilter();
        expected.setFiltered(true);
        expected.setId(1L);

        mockMvc.perform(get("/measure/listById")
                .param("truckId", "1"))

                .andExpect(view().name("measureList"))
                .andExpect(model().attributeExists("marker", "truckList", "measureDTOList"))
                .andExpect(model().attribute("marker", expected))
                .andDo(print());
    }

    @Test
    public void listOverloaded_viewMeasureListOverloaded() throws Exception {
        MarkerOfFilter expected = new MarkerOfFilter();
        expected.setFiltered(false);
        mockMvc.perform(get("/measure/listOfOverloaded"))
                .andExpect(view().name("measureList"))
                .andExpect(model().attributeExists("marker", "truckList", "measureDTOList"))
                .andExpect(model().attribute("marker", expected))
                .andDo(print());
    }

    @Test
    public void listNotOverloaded_viewMeasureListNotOverloaded() throws Exception {
        MarkerOfFilter expected = new MarkerOfFilter();
        expected.setFiltered(false);
        mockMvc.perform(get("/measure/listOfNotOverloaded"))
                .andExpect(view().name("measureList"))
                .andExpect(model().attributeExists("marker", "truckList", "measureDTOList"))
                .andExpect(model().attribute("marker", expected))
                .andDo(print());
    }

    @Test
    public void listOverloadedById_viewMeasureListByIdOverloaded() throws Exception {
        MarkerOfFilter expected = new MarkerOfFilter();
        expected.setFiltered(true);
        expected.setId(1L);
        mockMvc.perform(get("/measure/listOfOverloaded/{id}", 1L))
                .andExpect(view().name("measureList"))
                .andExpect(model().attributeExists("marker", "truckList", "measureDTOList"))
                .andExpect(model().attribute("marker", expected))
                .andDo(print());
    }


    @Test
    public void listNotOverloadedById_viewMeasureListByIdNotOverloaded() throws Exception {
        MarkerOfFilter expected = new MarkerOfFilter();
        expected.setFiltered(true);
        expected.setId(1L);
        mockMvc.perform(get("/measure/listOfNotOverloaded/{id}", 1L))
                .andExpect(view().name("measureList"))
                .andExpect(model().attributeExists("marker", "truckList", "measureDTOList"))
                .andExpect(model().attribute("marker", expected))
                .andDo(print());
    }


}
