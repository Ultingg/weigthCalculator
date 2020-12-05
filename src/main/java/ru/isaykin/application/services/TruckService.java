package ru.isaykin.application.services;

import org.springframework.stereotype.Service;
import ru.isaykin.application.logic.model.Truck;
import ru.isaykin.application.repositories.TruckRepository;

import java.util.List;

@Service
public class TruckService {

    private final TruckRepository truckRepository;


    public TruckService(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    public Iterable<Truck> getAll() {
        return truckRepository.findAll();
    }

    public List<Truck> getAll2() {
        return truckRepository.getAll();
    }

    public Truck addTruck(Truck truck) {
        truckRepository.create(truck.getFirstWheelWeight()
                , truck.getFrontPrice()
                , truck.getRearPrice()
                , truck.getTruckNumber()
                , truck.getTruckWeight());

        return truckRepository.getByTruckNumber(truck.getTruckNumber());
    }

    public Truck getTruck(Long id) {
        return truckRepository.getById(id);
    }

    public boolean deleteById(Long id) {
        Truck truckToDelete = truckRepository.getById(id);
        boolean response;
        if (truckToDelete == null) {
            response = false;
        } else {
            response = true;
            truckRepository.deleteById(id);
        }
        return response;

    }

}
