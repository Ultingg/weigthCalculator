package ru.isaykin.application.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.isaykin.application.logic.model.Measure;
import ru.isaykin.application.logic.model.Truck;
import ru.isaykin.application.services.MeasureService;
import ru.isaykin.application.services.TruckService;

import static org.springframework.http.HttpStatus.*;

@Controller
public class MeasureController {

    private final TruckService truckService;
    private final MeasureService measureService;

    public MeasureController(TruckService truckService, MeasureService measureService) {
        this.truckService = truckService;
        this.measureService = measureService;
    }

    @PostMapping("trucks/{id}/measure")
    public ResponseEntity<?> createMeasure(@RequestParam(name = "front") double frontBar,
                                           @RequestParam(name = "rear") double rearBar,
                                           @PathVariable Long id) {
        Truck truckToMeasure = truckService.getTruck(id);
        Measure measureToBackAsResponse = measureService.create(truckToMeasure, frontBar,rearBar);
        ResponseEntity<?> responseEntity;

        if(truckToMeasure == null) {
            responseEntity = new ResponseEntity<>("There is no such Truck in database", NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity<Measure>(measureToBackAsResponse, OK);
        }
        return responseEntity;

    }
    @GetMapping("trucks/measure/{id}")
    public ResponseEntity<?> getMeasureById(@PathVariable Long id) {
        Measure measureToResponse = measureService.getById(id);
        ResponseEntity<?> responseEntity;
        if(measureToResponse == null) {
            responseEntity = new ResponseEntity<>("Measure with id " + id +" not found", NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity<>(measureToResponse, OK);
        }
        return responseEntity;

    }



}
