package ru.isaykin.application.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.isaykin.application.model.Measure;
import ru.isaykin.application.model.Truck;
import ru.isaykin.application.repositories.MeasureRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@Service
@Component
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
                , createdMeasure.isOverloaded());
        return measureRepository.getByDateOfMeasure(createdMeasure.getDateOfMeasure());
    }

    public Measure getById(Long id) {
        return measureRepository.getById(id);
    }

    public Measure getByDateOfMeasure(LocalDateTime dateOfMeasure) {
        return measureRepository.getByDateOfMeasure(dateOfMeasure);
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
        return measureRepository.getListOfMeasuresByTruckId(id);
    }

    public List<Measure> getListOfOverloads(boolean overload) {
        return measureRepository.getListOfOverloadedMeasures(overload);
    }


}
