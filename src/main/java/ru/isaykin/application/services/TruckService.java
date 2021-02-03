package ru.isaykin.application.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.isaykin.application.exceptions.NoTruckException;
import ru.isaykin.application.model.Measure;
import ru.isaykin.application.model.Truck;
import ru.isaykin.application.repositories.MeasureRepository;
import ru.isaykin.application.repositories.TruckRepository;

import java.util.List;

@Slf4j
@Service
@Component
public class TruckService {


    private final TruckRepository truckRepository;
    private final MeasureRepository measureRepository;


    public TruckService(TruckRepository truckRepository, MeasureRepository measureRepository) {
        this.truckRepository = truckRepository;
        this.measureRepository = measureRepository;
    }

    public List<Truck> getAll() {
        return truckRepository.getAll();
    }

    public void addTruck(Truck truck) {
        truckRepository.create(truck.getFirstWheelWeight()
                , truck.getFrontPrice()
                , truck.getRearPrice()
                , truck.getTruckNumber()
                , truck.getTruckWeight());
    }

    public Truck getTruck(Long id) {
        Truck result = truckRepository.getById(id);
        if (result == null) throw new NoTruckException("There is no Truck in database with such id");


        return result;
    }

    public void deleteById(Long id) {
        if(truckRepository.getById(id) == null)  throw new NoTruckException("There is no Truck in database with such id");
        List<Measure> measureList = measureRepository.getAll();
        for (Measure measure : measureList) {
            if (measure.getTruckId().equals(id)) measureRepository.deleteById(measure.getId());
        }
        truckRepository.deleteById(id);
    }

    public void updateById(Long id, Truck updatedTruck) {
        Truck truckToUpdate = truckRepository.getById(id);
        if (truckToUpdate == null) {
            throw new NoTruckException("There is no Truck in database with such id");
        } else {
            updatedTruck.setId(id);
            truckRepository.save(updatedTruck);
        }
    }
}
