package ru.isaykin.application.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.isaykin.application.model.MarkerOfFilter;
import ru.isaykin.application.model.Measure;
import ru.isaykin.application.model.Truck;
import ru.isaykin.application.services.MeasureService;
import ru.isaykin.application.services.TruckService;

import javax.validation.Valid;
import java.util.List;

//TODO: exceptionHandler for internalErrors when there is no Truck for measure and so on
@Slf4j
@Controller
public class MeasureController {

    private final TruckService truckService;
    private final MeasureService measureService;

    public MeasureController(TruckService truckService, MeasureService measureService) {
        this.truckService = truckService;
        this.measureService = measureService;
    }
    @ModelAttribute("marker")
    public MarkerOfFilter getMarker() {
        return new MarkerOfFilter(false);
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
    public String getMeasuresByTruckId(@RequestParam(name = "truckId") Long id, Model model) {
        List<Measure> measureList = measureService.getListOfMeasuresByTruckId(id);
        MarkerOfFilter markerOfFilter = new MarkerOfFilter(true,id);
        model.addAttribute("marker", markerOfFilter);
        model.addAttribute("measureList", measureList);
        return "measureList";
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
        Measure measure = measureService.create(
                truckService.getTruck(newMeasure.getTruckId())
                , newMeasure.getFrontBar()
                , newMeasure.getRearBar());
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

    @GetMapping("measure/listOfOverloaded")
    public String getListOfOverloaded(Model model){
        MarkerOfFilter marker = new MarkerOfFilter(false);
        model.addAttribute("marker", marker);
       model.addAttribute("measureList", measureService.getListOfOverloaded());
        return "measureList";
    }

    @GetMapping("measure/listOfOverloaded/{id}")
    public String getListOfOverloadedAndByTruckId(@PathVariable("id") Long id, Model model) {
        List<Measure> listOfOverloadedAndById = measureService.getListOfOverloadedAndByTruckId(id);
        MarkerOfFilter marker = new MarkerOfFilter(true, id);
        model.addAttribute("measureList", listOfOverloadedAndById);
        model.addAttribute("marker", marker);
        return "measureList";
    }
    @GetMapping("measure/listOfNotOverloaded")
    public String getListOfNotOverloaded(Model model){
        MarkerOfFilter marker = new MarkerOfFilter(false);
        model.addAttribute("measureList", measureService.getListOfNotOverloaded());
        model.addAttribute("marker", marker);
        return "measureList";
    }

    @GetMapping("measure/listOfNotOverloaded/{id}")
    public String getListOfNotOverloadedAndByTruckId( @PathVariable("id") Long id, Model model) {
        List<Measure> listOfOverloadedAndById = measureService.getListOfNotOverloadedAndByTruckId(id);
        MarkerOfFilter marker = new MarkerOfFilter(true, id);
        model.addAttribute("measureList", listOfOverloadedAndById);
        model.addAttribute("marker", marker);
        return "measureList";
    }

}
