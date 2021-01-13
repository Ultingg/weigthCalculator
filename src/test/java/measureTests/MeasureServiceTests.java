package measureTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.isaykin.application.model.Measure;
import ru.isaykin.application.model.MeasureDTO;
import ru.isaykin.application.model.Truck;
import ru.isaykin.application.repositories.MeasureRepository;
import ru.isaykin.application.repositories.TruckRepository;
import ru.isaykin.application.services.MeasureService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class MeasureServiceTests {

    private MeasureRepository measureRepository;
    private TruckRepository truckRepository;
    private MeasureService measureService;


    @BeforeEach
    void setUpMocks() {
        measureRepository = mock(MeasureRepository.class);
        truckRepository = mock(TruckRepository.class);
        measureService = new MeasureService(measureRepository, truckRepository);
    }


    @Nested
    class MeasuresListsTests {
        List<Measure> measureList;
        List<Measure> measureListOverloaded;
        List<Measure> measureListNotOverloaded;
        List<Truck> truckList;
        List<MeasureDTO> measureDTOList;
        List<MeasureDTO> measureDTOListOverloaded;
        List<MeasureDTO> measureDTOListNotOverloaded;
        List<MeasureDTO> measureDTOListOverloadedById;
        List<MeasureDTO> measureDTOListNotOverloadedById;
        List<MeasureDTO> measureDTOListByTruckId;

        @BeforeEach
        void setUpMeasureListAndTruckList() {
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
            Measure measure2 = Measure.builder()
                    .dateOfMeasure(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                    .cargoWeight(23500)
                    .frontBar(5)
                    .rearBar(4)
                    .frontWeight(14000)
                    .rearWeight(28000)
                    .completeWeight(40000)
                    .completeOverloaded(false)
                    .frontOverloaded(true)
                    .rearOverloaded(false)
                    .overloaded(true)
                    .id(2L)
                    .truckId(2L)
                    .build();
            Measure measure3 = Measure.builder()
                    .dateOfMeasure(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                    .cargoWeight(21700)
                    .frontBar(6)
                    .rearBar(2)
                    .frontWeight(18600)
                    .rearWeight(1420)
                    .completeWeight(38200)
                    .completeOverloaded(false)
                    .frontOverloaded(true)
                    .rearOverloaded(false)
                    .overloaded(false)
                    .id(3L)
                    .truckId(1L)
                    .build();
            Measure measure4 = Measure.builder()
                    .dateOfMeasure(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                    .cargoWeight(16500)
                    .frontBar(4.5)
                    .rearBar(3)
                    .frontWeight(12000)
                    .rearWeight(15000)
                    .completeWeight(33000)
                    .completeOverloaded(false)
                    .frontOverloaded(false)
                    .rearOverloaded(false)
                    .overloaded(false)
                    .id(4L)
                    .truckId(2L)
                    .build();
            measureList = Arrays.asList(measure, measure2, measure3, measure4);
            measureListOverloaded = Arrays.asList(measure2, measure3);
            measureListNotOverloaded = Arrays.asList(measure, measure4);

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

            MeasureDTO measureDTO = new MeasureDTO();
            measureDTO.setTruckNumber("A324HO47");
            measureDTO.setDateOfMeasure(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
            measureDTO.setCargoWeight(24800);
            measureDTO.setFrontBar(5);
            measureDTO.setRearBar(3);
            measureDTO.setFrontWeight(14600);
            measureDTO.setRearWeight(21300);
            measureDTO.setCompleteWeight(41300);
            measureDTO.setCompleteOverloaded(false);
            measureDTO.setFrontOverloaded(false);
            measureDTO.setRearOverloaded(false);
            measureDTO.setOverloaded(false);
            measureDTO.setId(1L);
            measureDTO.setTruckId(1L);
            MeasureDTO measureDTO2 = new MeasureDTO();
            measureDTO2.setTruckNumber("B789YE98");
            measureDTO2.setDateOfMeasure(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
            measureDTO2.setCargoWeight(23500);
            measureDTO2.setFrontBar(5);
            measureDTO2.setRearBar(4);
            measureDTO2.setFrontWeight(14000);
            measureDTO2.setRearWeight(28000);
            measureDTO2.setCompleteWeight(40000);
            measureDTO2.setCompleteOverloaded(false);
            measureDTO2.setFrontOverloaded(true);
            measureDTO2.setRearOverloaded(false);
            measureDTO2.setOverloaded(true);
            measureDTO2.setId(2L);
            measureDTO2.setTruckId(2L);
            MeasureDTO measureDTO3 = new MeasureDTO();
            measureDTO3.setTruckNumber("A324HO47");
            measureDTO3.setDateOfMeasure(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
            measureDTO3.setCargoWeight(21700);
            measureDTO3.setFrontBar(6);
            measureDTO3.setRearBar(2);
            measureDTO3.setFrontWeight(18600);
            measureDTO3.setRearWeight(14200);
            measureDTO3.setCompleteWeight(38200);
            measureDTO3.setCompleteOverloaded(false);
            measureDTO3.setFrontOverloaded(true);
            measureDTO3.setRearOverloaded(false);
            measureDTO3.setOverloaded(true);
            measureDTO3.setId(3L);
            measureDTO3.setTruckId(1L);
            MeasureDTO measureDTO4 = new MeasureDTO();
            measureDTO4.setTruckNumber("B789YE98");
            measureDTO4.setDateOfMeasure(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
            measureDTO4.setCargoWeight(16500);
            measureDTO4.setFrontBar(4.5);
            measureDTO4.setRearBar(3);
            measureDTO4.setFrontWeight(12000);
            measureDTO4.setRearWeight(15000);
            measureDTO4.setCompleteWeight(33000);
            measureDTO4.setCompleteOverloaded(false);
            measureDTO4.setFrontOverloaded(false);
            measureDTO4.setRearOverloaded(false);
            measureDTO4.setOverloaded(false);
            measureDTO4.setId(4L);
            measureDTO4.setTruckId(2L);
            measureDTOList = Arrays.asList(measureDTO, measureDTO2, measureDTO3, measureDTO4);
            measureDTOListOverloaded = Arrays.asList(measureDTO2, measureDTO3);
            measureDTOListNotOverloaded = Arrays.asList(measureDTO, measureDTO4);
            measureDTOListOverloadedById = Arrays.asList(measureDTO3);
            measureDTOListNotOverloadedById = Arrays.asList(measureDTO);
            measureDTOListByTruckId = Arrays.asList(measureDTO, measureDTO3);
        }


        @Test
        public void getAll_validRequest_listOfAllMeasuresDTO() {
            measureRepository = mock(MeasureRepository.class);
            truckRepository = mock(TruckRepository.class);
            measureService = new MeasureService(measureRepository, truckRepository);
            when(measureRepository.getAll()).thenReturn(measureList);
            when(truckRepository.getAll()).thenReturn(truckList);
            List<MeasureDTO> expected = measureDTOList;

            List<MeasureDTO> actual = measureService.getListOfAllMeasuresDTO();

            assertEquals(expected, actual, "Get List of all MeasureDTO");
            verify(measureRepository, times(1)).getAll();
            verify(truckRepository, times(1)).getAll();
        }

        @Test
        public void getAll_validRequest_listOfOverloadedMeasureDTO() {
            measureRepository = mock(MeasureRepository.class);
            truckRepository = mock(TruckRepository.class);
            measureService = new MeasureService(measureRepository, truckRepository);
            when(measureRepository.getMeasureByOverloaded(true)).thenReturn(measureListOverloaded);
            when(truckRepository.getAll()).thenReturn(truckList);
            List<MeasureDTO> expected = measureDTOListOverloaded;

            List<MeasureDTO> actual = measureService.getListOfOverloadedMeasureDTO();

            assertEquals(expected, actual, "Get List of Overloaded MeasureDTO");
            verify(measureRepository, times(1)).getMeasureByOverloaded(true);
            verify(measureRepository, times(1)).getMeasureByOverloaded(anyBoolean());
            verify(truckRepository, times(1)).getAll();
        }

        @Test
        public void getAll_validRequest_listOfNotOverloadedMeasureDTO() {
            measureRepository = mock(MeasureRepository.class);
            truckRepository = mock(TruckRepository.class);
            measureService = new MeasureService(measureRepository, truckRepository);
            when(measureRepository.getMeasureByOverloaded(false)).thenReturn(measureListNotOverloaded);
            when(truckRepository.getAll()).thenReturn(truckList);
            List<MeasureDTO> expected = measureDTOListNotOverloaded;

            List<MeasureDTO> actual = measureService.getListOfNotOverloadedMeasureDTO();

            assertEquals(expected, actual, "Get List of Not Overloaded MeasureDTO");
            verify(measureRepository, times(1)).getMeasureByOverloaded(false);
            verify(measureRepository, times(1)).getMeasureByOverloaded(anyBoolean());
            verify(truckRepository, times(1)).getAll();
        }

        @Test
        public void getListMeasureDTOOverloadedByTruckId_validId_list() {
            measureRepository = mock(MeasureRepository.class);
            truckRepository = mock(TruckRepository.class);
            measureService = new MeasureService(measureRepository, truckRepository);
            when(measureRepository.getMeasureByOverloaded(true)).thenReturn(measureListOverloaded);
            when(truckRepository.getById(1L)).thenReturn(truckList.get(0));
            List<MeasureDTO> expected = measureDTOListOverloadedById;

            List<MeasureDTO> actual = measureService.getListOfOverloadedAndByTruckIdDTO(1L);

            assertEquals(expected, actual, "Get list of Overloaded MeasureDTO by TruckId(TruckNumber)");
            verify(truckRepository, times(1)).getById(1L);
            verify(truckRepository, times(1)).getById(anyLong());
            verify(measureRepository, times(1)).getMeasureByOverloaded(true);
            verify(measureRepository, times(1)).getMeasureByOverloaded(anyBoolean());
        }

        @Test
        public void getListMeasureDTONotOverloadedByTruckId_validId_list() {
            measureRepository = mock(MeasureRepository.class);
            truckRepository = mock(TruckRepository.class);
            measureService = new MeasureService(measureRepository, truckRepository);
            when(measureRepository.getMeasureByOverloaded(false)).thenReturn(measureListNotOverloaded);
            when(truckRepository.getById(1L)).thenReturn(truckList.get(0));
            List<MeasureDTO> expected = measureDTOListNotOverloadedById;

            List<MeasureDTO> actual = measureService.getListOfNotOverloadedAndByTruckIdDTO(1L);

            assertEquals(expected, actual, "Get list of Not Overloaded MeasureDTO by TruckId(TruckNumber)");
            verify(truckRepository, times(1)).getById(1L);
            verify(truckRepository, times(1)).getById(anyLong());
            verify(measureRepository, times(1)).getMeasureByOverloaded(false);
            verify(measureRepository, times(1)).getMeasureByOverloaded(anyBoolean());
        }

        @Test
        public void getListMeasureDTOByTruckId_validId_list() {
            measureRepository = mock(MeasureRepository.class);
            truckRepository = mock(TruckRepository.class);
            measureService = new MeasureService(measureRepository, truckRepository);
            when(measureRepository.getAll()).thenReturn(measureList);
            when(truckRepository.getById(1L)).thenReturn(truckList.get(0));
            List<MeasureDTO> expected = measureDTOListByTruckId;

            List<MeasureDTO> actual = measureService.getListOfMeasureDTOByTruckId(1L);

            assertEquals(expected, actual, "Get List of MeasureDTO by TruckId(TruckNumber)");
            verify(measureRepository, times(1)).getAll();
            verify(truckRepository, times(1)).getById(1L);
            verify(truckRepository, times(1)).getById(anyLong());
        }

        @Test
        public void create_validData_Measure() {
            Truck truck = truckList.get(0);
            double frontBar = 5;
            double rearBar = 3;
            Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
            Measure expected = Measure.builder()
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
            when(measureRepository.getByDateOfMeasure(timestamp)).thenReturn(expected);

            Measure actual = measureService.create(truck, frontBar, rearBar);

            assertEquals(expected, actual, "Creating Measure in Measure Service and returns it by timestamp");
            verify(measureRepository,times(1)).getByDateOfMeasure(timestamp);
            verify(measureRepository,times(1)).getByDateOfMeasure(any(Timestamp.class));
            verify(measureRepository,times(1)).create(24800,41300,timestamp,5,14600,
                    3,21300,1L,false,false,false,false);
            verify(measureRepository,times(1)).create(anyDouble(),anyDouble(),any(Timestamp.class),anyDouble(),anyDouble(),
                    anyDouble(),anyDouble(),anyLong(),anyBoolean(),anyBoolean(),anyBoolean(),anyBoolean());


        }

    }

    @Test
    public void deleteById_validId_CorrectTimesOfCall() {
        measureService.deleteById(1L);

        verify(measureRepository, times(1)).deleteById(anyLong());
        verify(measureRepository, times(1)).deleteById(1L);
    }


    @Test
    public void getById_validId_MeasureById() {

        measureRepository = mock(MeasureRepository.class);
        truckRepository = mock(TruckRepository.class);
        measureService = new MeasureService(measureRepository, truckRepository);
        Measure expected = new Measure();
        expected.setId(1L);
        when(measureRepository.getById(1L)).thenReturn(expected);

        Measure actual = measureService.getById(1L);

        assertEquals(expected, actual, "Get Measure by id = 1L");
        verify(measureRepository, times(1)).getById(1L);
        verify(measureRepository, times(1)).getById(anyLong());
    }

    @Test
    public void getById_notExistingId_Null() {
        measureRepository = mock(MeasureRepository.class);
        truckRepository = mock(TruckRepository.class);
        measureService = new MeasureService(measureRepository, truckRepository);
        Measure expected = new Measure();
        expected.setId(1L);
        when(measureRepository.getById(1L)).thenReturn(expected);

        Measure actual = measureService.getById(10L);

        assertNull(actual);
        verify(measureRepository, times(1)).getById(10L);
        verify(measureRepository, times(1)).getById(anyLong());
    }


}
