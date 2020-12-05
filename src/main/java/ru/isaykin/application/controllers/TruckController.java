package ru.isaykin.application.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.isaykin.application.logic.model.Truck;
import ru.isaykin.application.services.TruckService;

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
        Iterable<Truck> truckList = truckService.getAll2();
        if (truckList == null) {
            responseEntity = new ResponseEntity<>(NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity<>(truckList, OK);
        }
        return responseEntity;
    }

    @PostMapping("trucks")
    public ResponseEntity<?> insertTruck(@RequestBody Truck truck) {
        ResponseEntity<Truck> responseEntity;
        if (truck == null) {
            responseEntity = new ResponseEntity<>(NO_CONTENT);
        } else {
            Truck truckToShow = truckService.addTruck(truck);
            responseEntity = new ResponseEntity<Truck>(truckToShow, OK);
        }
        return responseEntity;
    }

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

    @DeleteMapping("trucks/{id}")
    public ResponseEntity<?> deleteTruck(@PathVariable Long id) {
        ResponseEntity<?> responseEntity;
        boolean serviceResponse = truckService.deleteById(id);
        if (serviceResponse) {
            responseEntity = new ResponseEntity<>("Truck with id " + id + " was deleted", OK);
        } else {
            responseEntity = new ResponseEntity<>("Truck with this number not found", NOT_FOUND);
        }
        return responseEntity;
    }
    @PutMapping("trucks/{id}")
    public ResponseEntity<?> updateTruck(@PathVariable Long id, @RequestBody Truck updatedTruck) {
            ResponseEntity<?> responseEntity;
            Truck truckToUpdate = truckService.updateById(id, updatedTruck);
            if(truckToUpdate == null) {
                responseEntity = new ResponseEntity<>(NOT_MODIFIED);
            } else {
                responseEntity = new ResponseEntity<>(truckToUpdate, OK);
            }
            return responseEntity;
    }
}
