package ru.isaykin.application.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.isaykin.application.model.Measure;
import ru.isaykin.application.model.Truck;
import ru.isaykin.application.services.MeasureService;
import ru.isaykin.application.services.TruckService;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

//TODO: exceptionHandler for internalErrors when there is no Truck for measure and so on

@Controller
public class MeasureController {

    private final TruckService truckService;
    private final MeasureService measureService;

    public MeasureController(TruckService truckService, MeasureService measureService) {
        this.truckService = truckService;
        this.measureService = measureService;
    }
    @GetMapping("measure")
    public String measurePanel(Model model){
        List<Truck> truckList = truckService.getAll2();
        model.addAttribute("truckList", truckList);
        
        return "measurePanel";
    }
//    @PostMapping("measureUpdate")
//    public String measureUpdate(Model model){
//
//    model.addAttribute("truck2");
//
//    return "forward:/measurePanel";
//    }
    
    @PostMapping("trucks/{id}/measure")
    public ResponseEntity<?> createMeasure(@RequestParam(name = "front") double frontBar,
                                           @RequestParam(name = "rear") double rearBar,
                                           @PathVariable Long id) {
        Truck truckToMeasure = truckService.getTruck(id);
        Measure measureToBackAsResponse = measureService.create(truckToMeasure, frontBar, rearBar);
        ResponseEntity<?> responseEntity;

        if (truckToMeasure == null) {
            responseEntity = new ResponseEntity<>("There is no such Truck in database", NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity<>(measureToBackAsResponse, OK);
        }
        return responseEntity;
    }

    @GetMapping("trucks/measure/{id}")
    public ResponseEntity<?> getMeasureById(@PathVariable Long id) {
        Measure measureToResponse = measureService.getById(id);
        ResponseEntity<?> responseEntity;
        if (measureToResponse == null) {
            responseEntity = new ResponseEntity<>("Measure with id " + id + " not found", NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity<>(measureToResponse, OK);
        }
        return responseEntity;
    }

    @DeleteMapping("trucks/measure/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        ResponseEntity<?> responseEntity;
        boolean response = measureService.deleteById(id);
        if(response) {
            responseEntity = new ResponseEntity<>(OK);
        } else {
            responseEntity = new ResponseEntity<>(NOT_FOUND);
        }
        return responseEntity;
    }

    @GetMapping("trucks/measure")
    public ResponseEntity<?> getAll() {
        List<Measure> measureList = measureService.getAll();
        return new ResponseEntity<>(measureList, OK);
    }

}
