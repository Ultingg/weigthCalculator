package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.isaykin.application.model.Truck;
import ru.isaykin.application.repositories.MeasureRepository;
import ru.isaykin.application.repositories.TruckRepository;
import ru.isaykin.application.services.TruckService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TruckServiceTests {

    private TruckRepository truckRepository;
    private MeasureRepository measureRepository;
    private TruckService truckService;
    private List<Truck> truckList;


    @BeforeEach
    void setUp() {
        truckRepository = mock(TruckRepository.class);
        measureRepository = mock(MeasureRepository.class);
        truckService = new TruckService(truckRepository, measureRepository);
        Truck truck = Truck.builder()
                .id(1L)
                .truckNumber("A324HO47")
                .truckWeight(16500)
                .firstWheelWeight(5400)
                .frontPrice(400)
                .rearPrice(710)
                .build();
        Truck truck1 = Truck.builder()
                .id(2L)
                .truckNumber("B789YE98")
                .truckWeight(16500)
                .firstWheelWeight(6000)
                .frontPrice(400)
                .rearPrice(500)
                .build();
        truckList = Arrays.asList(truck, truck1);
    }

    @Test
    public void getAll_ListOfTrucks() {
        when(truckRepository.getAll()).thenReturn(truckList);
        List<Truck> expected = truckList;

        List<Truck> actual = truckService.getAll2();

        assertEquals(expected, actual, "Get list of all trucks from Truck Repository");
        verify(truckRepository, times(1)).getAll();
    }

    @Test
    public void getTruckById_validId_Truck() {
        Truck expected = Truck.builder()
                .id(1L)
                .truckNumber("B789YE98")
                .truckWeight(16500)
                .firstWheelWeight(6000)
                .frontPrice(400)
                .rearPrice(500)
                .build();
        when(truckRepository.getById(1L)).thenReturn(expected);

        Truck actual = truckService.getTruck(1L);

        assertEquals(expected, actual, "Get truck by id");
        verify(truckRepository, times(1)).getById(1L);
        verify(truckRepository, times(1)).getById(anyLong());

    }

    @Test
    public void addTruck_validTruck_verifying() {
        Truck truck = Truck.builder()
                .truckNumber("B789YE98")
                .truckWeight(16500)
                .firstWheelWeight(6000)
                .frontPrice(400)
                .rearPrice(500)
                .build();
        truckService.addTruck(truck);

        verify(truckRepository, times(1)).create(6000, 400, 500, "B789YE98", 16500);
        verify(truckRepository, times(1)).create(anyDouble(), anyDouble(), anyDouble(), anyString(), anyDouble());
    }

    @Test
    public void updateTuck_null_NPException() {

        assertThrows(NullPointerException.class, () -> truckService.updateById(1L, null));

    }

    @Test
    public void updateTruckById_validId_Truck() {

        Truck oldTruck = Truck.builder()
                .id(2L)
                .truckNumber("B789YE98")
                .truckWeight(16500)
                .firstWheelWeight(6000)
                .frontPrice(400)
                .rearPrice(500)
                .build();
//        when(truckRepository.getById(2L)).thenReturn(oldTruck);
        Truck updatedTruck = Truck.builder()
                .id(2L)
                .truckNumber("B789YE98")
                .truckWeight(16500)
                .firstWheelWeight(5500)
                .frontPrice(300)
                .rearPrice(650)
                .build();
        Truck expected = Truck.builder()
                .id(2L)
                .truckNumber("B789YE98")
                .truckWeight(16500)
                .firstWheelWeight(5500)
                .frontPrice(300)
                .rearPrice(650)
                .build();


//        truckService.updateById(1L, null);
        assertThrows(NullPointerException.class, () -> truckService.updateById(null, updatedTruck));


    }
}
