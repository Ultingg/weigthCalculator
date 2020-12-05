package MeasureTests;

import org.junit.jupiter.api.Test;
import ru.isaykin.application.logic.model.Measure;
import ru.isaykin.application.repositories.MeasureRepository;
import ru.isaykin.application.services.MeasureService;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MeasureServiceTests {

    private MeasureService measureService;
    private MeasureRepository measureRepository;


    @Test
    void getByDateOfMeasure() {
        measureRepository = mock(MeasureRepository.class);
        measureService = new MeasureService(measureRepository);
        Measure measure = new Measure();
        when(measureRepository.getByDateOfMeasure(Timestamp.valueOf("2020-12-05 09:26:12.578099")))
                .thenReturn(measure);
        Measure expected = measure;

        Measure actual = measureService.getByDateOfMeasure(Timestamp.valueOf("2020-12-05 09:26:12.578099"));

        assertEquals(expected, actual);
    }


}
