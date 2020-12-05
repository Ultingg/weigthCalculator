package ru.isaykin.application.services;

import org.springframework.stereotype.Service;
import ru.isaykin.application.logic.model.Truck;
import ru.isaykin.application.repositories.TruckRepository;

import java.util.Optional;

@Service
public class TruckService {

    private final TruckRepository truckRepository;


    public TruckService(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    public Iterable<Truck> getAll() {
        return truckRepository.findAll();
    }

//    public Truck addTruck(Truck truck) {
//        truckRepository.insert(truck.getFirstWheelWeight()
//                , truck.getFrontPrice()
//                , truck.getRearPrice()
//                , truck.getTruckNumber()
//                , truck.getTruckWeight());
//        return truckRepository.getByTruckNumber(truck.getTruckNumber());
//    }
    public Truck getTruck(Long id) {
       return truckRepository.getById(id);
    }

}
