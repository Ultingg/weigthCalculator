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
import ru.isaykin.application.repositories.MeasureRepository;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class)
public class MeasureControllerMVCTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MeasureRepository measureRepository;

    @Autowired
    private MeasureController measureController;


    @Test
    public void mainPage() throws Exception {
        mockMvc.perform(get("/measure"))
                .andExpect(view().name("measureList"));
        //   .andExpect(MockMvcResultMatchers.content().string(containsString("Отфильтровать измерения по номеру ТС")));

    }

    @Test
    public void measurePanelView() throws Exception {
        mockMvc.perform(get("/measure"))
                .andExpect(view().name("measureList")).
                andExpect(MockMvcResultMatchers.content().string(containsString("Отфильтровать измерения по номеру ТС")));

    }


}
