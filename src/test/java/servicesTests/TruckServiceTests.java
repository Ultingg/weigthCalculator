package servicesTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.isaykin.application.model.Measure;
import ru.isaykin.application.model.Truck;
import ru.isaykin.application.repositories.MeasureRepository;
import ru.isaykin.application.repositories.TruckRepository;
import ru.isaykin.application.services.TruckService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TruckServiceTests {

    private TruckService truckService;
    private TruckRepository truckRepository;
    private MeasureRepository measureRepository;
    private Truck truck;
    private List<Truck> truckList;
    private List<Measure> measureList;

    @BeforeEach
    public void setUpMock() {
        measureRepository = mock(MeasureRepository.class);
        truckRepository = mock(TruckRepository.class);
        truckService = new TruckService(truckRepository, measureRepository);
    }

    @BeforeEach
    public void setUpList() {
        truck = Truck.builder()
                .truckNumber("POH-987/KON-245")
                .truckWeight(17000)
                .firstWheelWeight(5400)
                .frontPrice(450)
                .rearPrice(650)
                .id(1L)
                .build();
        Truck truck2 = Truck.builder()
                .truckNumber("WER-231/ERW-545")
                .truckWeight(16000)
                .firstWheelWeight(6000)
                .frontPrice(380)
                .rearPrice(710)
                .id(2L)
                .build();
        truckList = Arrays.asList(truck, truck2);
        Measure measure = Measure.builder()
                .dateOfMeasure(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .cargoWeight(24800)
                .frontBar(5)
                .rearBar(3)
                .frontWeight(14600)
                .rearWeight(21300)
                .completeWeight(41300)
                .completeOverloaded(false)
                .frontOverloaded(false)
                .rearOverloaded(false)
                .overloaded(false)
                .id(1L)
                .truckId(1L)
                .build();
        measureList = Arrays.asList(measure);

    }


    @Test
    public void getAll_valid_ListOfTrucks() {
        when(truckRepository.getAll()).thenReturn(truckList);
        List<Truck> expected = truckList;

        List<Truck> actual = truckService.getAll();

        assertEquals(expected, actual, "Checking if list of trucks is the same");
    }

    @Test
    public void getTruck_valid_truckValid() {
        Truck expected = truck;
        when(truckRepository.getById(1L)).thenReturn(expected);

        Truck actual = truckService.getTruck(1L);

        assertEquals(expected, actual, " Checking if service get the right truck by id");
    }

    @Test
    public void getTruck_notValid_truckValid() {
        Truck expected = truck;

        Truck actual = truckService.getTruck(null);

//        assertThrows(NullPointerException.class,()->truckService.getTruck(null));
    }

    @Test
    public void deleteTruck_validId_deleteTruckAndMeasures() {
        when(measureRepository.getAll()).thenReturn(measureList);

        truckService.deleteById(1L);

        verify(truckRepository, times(1)).deleteById(1L);
        verify(truckRepository, times(1)).deleteById(anyLong());

        verify(measureRepository, times(1)).deleteById(1L);
        verify(measureRepository, times(1)).deleteById(anyLong());
        verify(measureRepository, times(1)).getAll();
    }

    @Test
    public void updateTruckById_validId_updatedTruck() {
        Truck truckToUpdate = truck;
        Truck updatedTruck = Truck.builder()
                .truckNumber("TER-832/NEX-786")
                .truckWeight(17000)
                .firstWheelWeight(5400)
                .frontPrice(450)
                .rearPrice(650)
                .id(1L)
                .build();
        when(truckRepository.getById(1L)).thenReturn(truckToUpdate);

        truckService.updateById(1L, updatedTruck);

        verify(truckRepository, times(1)).getById(1L);
        verify(truckRepository, times(1)).getById(anyLong());
        verify(truckRepository, times(1)).save(any(Truck.class));

    }
}
