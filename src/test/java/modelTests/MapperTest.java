package modelTests;

import org.junit.jupiter.api.Test;
import ru.isaykin.application.DTO.MeasureDTO;
import ru.isaykin.application.mappers.MeasureMapper;
import ru.isaykin.application.model.Measure;
import ru.isaykin.application.model.Truck;

import java.nio.file.Paths;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapperTest {

    @Test
    public void mapTest() {

        Measure measure = Measure.builder().id(1L).cargoWeight(25000).completeWeight(41500)
                .dateOfMeasure(LocalDateTime.parse("2020-01-01T08:00:00")).frontBar(5.2).frontWeight(14800)
                .rearBar(3.45).rearWeight(20700).overloaded(false).truckId(1L).frontOverloaded(false)
                .rearOverloaded(false).completeOverloaded(false).build();
        Truck truck = Truck.builder()
                .id(1L).firstWheelWeight(6000).frontPrice(400).rearPrice(600)
                .truckWeight(16500).truckNumber("EGW-542/DNM-698").build();

        MeasureDTO measureDTO = MeasureMapper.INSTANCE.fromMeasure(measure, truck);

        assertEquals(measure.getCargoWeight(), measureDTO.getCargoWeight());
        assertEquals(truck.getTruckNumber(), measureDTO.getTruckNumber());
        assertEquals(measure.getDateOfMeasure(), measureDTO.getDateOfMeasure());
    }


}
