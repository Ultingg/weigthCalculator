package ru.isaykin.application.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.isaykin.application.model.Measure;
import ru.isaykin.application.model.Truck;
import ru.isaykin.application.services.MeasureService;
import ru.isaykin.application.services.TruckService;

import javax.validation.Valid;
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

    @ModelAttribute("measureList")
    public List<Measure> getMeasureListUtil() {
        return measureService.getAll();
    }

    @ModelAttribute("truckList")
    public List<Truck> getTruckListUtil() {
        return truckService.getAll2();
    }


    @GetMapping("measure")
    public String measurePanel(Model model) {
        return "measureList";
    }

    @GetMapping("measure/listById")
    public String getMeasuresByTruckId(@RequestParam(name = "truck") Long id, Model model) {
        List<Measure> measureList = measureService.getListOfMeasuresByTruckId(id);
        model.addAttribute("measureList", measureList);
        return "measureListId";
    }

    @ModelAttribute("greetingsMessage")
    public String showGreetingMessage() {
        return "Калькулятор весовых нагрузок";
    }

    @PostMapping("trucks/measure/created")
    public String measureRecipe(@ModelAttribute("measure")
                                @Valid Measure newMeasure,
                                BindingResult bindingResult,
                                Model model) {
        if(bindingResult.hasErrors()) {
            return "calculation";
        }
       // Truck checkingTruck = truckService.getTruck(newMeasure.getTruckId());//костыль бля
        Measure measure = measureService.create(truckService.getTruck(newMeasure.getTruckId()), newMeasure.getFrontBar(), newMeasure.getRearBar());
        model.addAttribute("measure", measure);
        return "measureRecipe";
    }

    @GetMapping("trucks/measure/new")
    public String calculation(@ModelAttribute("measure") Measure measure) {
        return "calculation";
    }

    @RequestMapping(value = "trucks/measure/delete/{id}", method = RequestMethod.DELETE)
    public String deleteMeasureByIdPage(@PathVariable Long id, Model model) {
        Measure deletedMeasure = measureService.getById(id);
        measureService.deleteById(id);
        model.addAttribute("measure", deletedMeasure);
        return "deleteMeasure";
    }

}
