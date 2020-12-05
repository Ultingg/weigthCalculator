package ru.isaykin.application.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.isaykin.application.logic.model.Truck;
import ru.isaykin.application.services.TruckService;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping
public class TruckController {

    private final TruckService truckService;

    public TruckController(TruckService truckService) {
        this.truckService = truckService;
    }


    @GetMapping("trucks")
    public ResponseEntity<Object> getAll() {
        ResponseEntity<Object> responseEntity;
        Iterable<Truck> truckList = truckService.getAll();
        if (truckList == null) {
            responseEntity = new ResponseEntity<>(NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity<Object>(truckList, OK);
        }
        return responseEntity;
    }
//    @PostMapping("trucks")
//    public ResponseEntity<Truck> insertTruck(@RequestBody Truck truck) {
//        ResponseEntity<Truck> responseEntity;
//        if (truck == null) {
//            responseEntity = new ResponseEntity<>(NO_CONTENT);
//        } else {
//            Truck truckToShow = truckService.addTruck(truck);
//            responseEntity = new ResponseEntity<Truck>(truckToShow, OK);
//        }
//        return responseEntity;
//    }

    @GetMapping("trucks/{id}")
    public ResponseEntity<?> getTruck(@PathVariable Long id) {
        ResponseEntity<?> responseEntity;
        Truck truck = truckService.getTruck(id);
        if (truck == null) {
            responseEntity = new ResponseEntity<>(NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity<Truck>(truck, OK);
        }

        return responseEntity;
    }
}
