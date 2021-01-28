package ru.isaykin.application.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.isaykin.application.DTO.MeasureDTO;
import ru.isaykin.application.mappers.MeasureMapper;
import ru.isaykin.application.model.Measure;
import ru.isaykin.application.model.Truck;
import ru.isaykin.application.repositories.MeasureRepository;
import ru.isaykin.application.repositories.TruckRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MeasureService {

    private final MeasureRepository measureRepository;
    private final TruckRepository truckRepository;

    public MeasureService(MeasureRepository measureRepository, TruckRepository truckRepository) {
        this.measureRepository = measureRepository;
        this.truckRepository = truckRepository;
    }


    public Measure addMeasure(Truck truck, double frontBar, double rearBar) {
        Measure createdMeasure = new Measure();
        createdMeasure.calcWeights(truck, frontBar, rearBar);
        measureRepository.create(createdMeasure.getCargoWeight()
                , createdMeasure.getCompleteWeight()
                , Timestamp.valueOf(createdMeasure.getDateOfMeasure())
                , frontBar
                , createdMeasure.getFrontWeight()
                , rearBar
                , createdMeasure.getRearWeight()
                , truck.getId()
                , createdMeasure.isOverloaded()
                , createdMeasure.isFrontOverloaded()
                , createdMeasure.isRearOverloaded()
                , createdMeasure.isCompleteOverloaded());
        Timestamp timestamp = timeConverter(createdMeasure.getDateOfMeasure());
        return measureRepository.getByDateOfMeasure(timestamp);
    }

    public Measure getById(Long id) {
        return measureRepository.getById(id);
    }


    public void deleteById(Long id) {
        measureRepository.deleteById(id);
    }

    public List<MeasureDTO> getListOfAllMeasuresDTO() {
        List<Measure> measureList = measureRepository.getAll();
        List<Truck> truckList = truckRepository.getAll();
        return getCustomizeListOfMeasureDTO(measureList, truckList);
    }

    public List<MeasureDTO> getListOfNotOverloadedMeasureDTO() {
        List<Measure> measureList = measureRepository.getMeasureByOverloaded(false);
        List<Truck> truckList = truckRepository.getAll();
        return getCustomizeListOfMeasureDTO(measureList, truckList);
    }

    public List<MeasureDTO> getListOfNotOverloadedAndByTruckIdDTO(Long id) {
        List<Measure> listOfOverloaded = measureRepository.getMeasureByOverloaded(false);
        Truck truck = truckRepository.getById(id);
        return getCustomizeListOfMeasureDTOWithCurrentTruck(listOfOverloaded, truck);
    }

    public List<MeasureDTO> getListOfOverloadedMeasureDTO() {
        List<Measure> measureList = measureRepository.getMeasureByOverloaded(true);
        List<Truck> truckList = truckRepository.getAll();
        return getCustomizeListOfMeasureDTO(measureList, truckList);
    }

    public List<MeasureDTO> getListOfOverloadedAndByTruckIdDTO(Long id) {
        List<Measure> listOfOverloaded = measureRepository.getMeasureByOverloaded(true);
        Truck truck = truckRepository.getById(id);
        return getCustomizeListOfMeasureDTOWithCurrentTruck(listOfOverloaded, truck);
    }

    public List<MeasureDTO> getListOfMeasureDTOByTruckId(Long id) {
        List<Measure> measureList = measureRepository.getAll();
        Truck truck = truckRepository.getById(id);
        return getCustomizeListOfMeasureDTOWithCurrentTruck(measureList, truck);
    }

    private Timestamp timeConverter(LocalDateTime date) {
        return Timestamp.valueOf(date);
    }

    private List<MeasureDTO> getCustomizeListOfMeasureDTO(List<Measure> measureList, List<Truck> truckList) {
        List<MeasureDTO> resultList = new ArrayList<>();
        Truck truck = new Truck();
        for (Measure measure : measureList) {
            for (Truck truckN : truckList) {
                if (truckN.getId().equals(measure.getTruckId())) {
                    truck = truckN;
                    break;
                }
            }
            MeasureDTO measureDTO =
                    MeasureMapper.INSTANCE.fromMeasure(measure, truck);
//                    new MeasureDTO(measure, truck);
            resultList.add(measureDTO);
        }
        return resultList;
    }

    private List<MeasureDTO> getCustomizeListOfMeasureDTOWithCurrentTruck(List<Measure> measureList, Truck truck) {
        List<MeasureDTO> resultList = new ArrayList<>();
        for (Measure measure : measureList) {
            if (measure.getTruckId().equals(truck.getId())) {
                MeasureDTO measureDTO =
                        MeasureMapper.INSTANCE.fromMeasure(measure, truck);
                resultList.add(measureDTO);
            }
        }
        return resultList;
    }


}
