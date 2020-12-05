package ru.isaykin.application.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.isaykin.application.logic.model.Measure;
import ru.isaykin.application.logic.model.Truck;
import ru.isaykin.application.repositories.MeasureRepository;

import java.sql.Date;
import java.sql.Timestamp;

import static java.sql.Date.*;


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
                , createdMeasure.getTruckNumber()
                , createdMeasure.getTruckWeight());
        return measureRepository.getByDateOfMeasure(Timestamp.valueOf(createdMeasure.getDateOfMeasure()));
    }

    public Measure getById(Long id) {
        return measureRepository.getById(id);
    }

    public Measure getByDateOfMeasure(Timestamp dateOfMeasure) {
        return measureRepository.getByDateOfMeasure(dateOfMeasure);
    }
}
