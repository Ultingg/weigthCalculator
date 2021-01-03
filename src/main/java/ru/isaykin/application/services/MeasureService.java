package ru.isaykin.application.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.isaykin.application.model.Measure;
import ru.isaykin.application.model.Truck;
import ru.isaykin.application.repositories.MeasureRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MeasureService {

    private final MeasureRepository measureRepository;

    public MeasureService(MeasureRepository measureRepository) {
        this.measureRepository = measureRepository;
    }

    public Measure create(Truck truck, double frontBar, double rearBar) {
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
        Timestamp timestamp = timeConvertor(createdMeasure.getDateOfMeasure());
        return measureRepository.getByDateOfMeasure(timestamp);
    }

    public Measure getById(Long id) {
        return measureRepository.getById(id);
    }

    public Measure getByDateOfMeasure(LocalDateTime dateOfMeasure) {
        Timestamp timestamp = timeConvertor(dateOfMeasure);
        return measureRepository.getByDateOfMeasure(timestamp);
    }

    public boolean deleteById(Long id) {
        Measure measureToDelete = measureRepository.getById(id);
        boolean response;
        if (measureToDelete == null) {
            response = false;
        } else {
            response = true;
            measureRepository.deleteById(id);
        }
        return response;
    }

    public List<Measure> getAll() {
        return measureRepository.getAll();
    }


    public List<Measure> getListOfMeasuresByTruckId(Long id) {
        List<Measure> measureList = measureRepository.getAll();
        List<Measure> measureListById = new ArrayList<>();
        for (Measure measure : measureList)
            if (measure.getTruckId().equals(id)) measureListById.add(measure);
        return measureListById;
    }


    public List<Measure> getListOfOverloaded() {
        return measureRepository.getMeasureByOverloaded(true);
    }

    public List<Measure> getListOfOverloadedAndByTruckId(Long id) {
        List<Measure> listOfOverloaded = measureRepository.getMeasureByOverloaded(true);
        List<Measure> sortedById = new ArrayList<>();
        for (Measure measure : listOfOverloaded) {

            if (measure.getTruckId().equals(id)) sortedById.add(measure);
        }
        return sortedById;
    }

    public List<Measure> getListOfNotOverloaded() {
        return measureRepository.getMeasureByOverloaded(false);
    }

    public List<Measure> getListOfNotOverloadedAndByTruckId(Long id) {
        List<Measure> listOfNotOverloaded = measureRepository.getMeasureByOverloaded(false);
        List<Measure> sortedById = new ArrayList<>();
        for (Measure measure : listOfNotOverloaded) {
            if (measure.getTruckId().equals(id)) sortedById.add(measure);
        }
        return sortedById;
    }

    private Timestamp timeConvertor(LocalDateTime date) {
        return Timestamp.valueOf(date);
    }


}
