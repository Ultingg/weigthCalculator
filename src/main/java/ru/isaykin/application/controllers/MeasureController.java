package ru.isaykin.application.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.isaykin.application.model.MarkerOfFilter;
import ru.isaykin.application.model.Measure;
import ru.isaykin.application.model.MeasureDTO;
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

    @ModelAttribute("truckList")
    public List<Truck> getTruckListUtil() {
        return truckService.getAll();
    }

    @ModelAttribute("measureDTOList")
    public List<MeasureDTO> getMeasureDTOListUtil() {
        return measureService.getListOfAllMeasuresDTO();
    }


    @GetMapping("measure")
    public String measurePanel() {
        return "measureList";
    }

    @GetMapping("measure/listById")
    public String getMeasuresByTruckId(@RequestParam(name = "truckId") Long id, Model model) {
        List<MeasureDTO> measureList = measureService.getListOfMeasureDTOByTruckId(id);
        MarkerOfFilter markerOfFilter = new MarkerOfFilter(true, id);
        model.addAttribute("marker", markerOfFilter);
        model.addAttribute("measureDTOList", measureList);
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
        if (bindingResult.hasErrors()) {
            return "calculation";
        }
        Truck truck = truckService.getTruck(newMeasure.getTruckId());
        Measure measure = measureService.addMeasure(
                truck
                , newMeasure.getFrontBar()
                , newMeasure.getRearBar());
        model.addAttribute("measure", measure);
        model.addAttribute("truck", truck);
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
        model.addAttribute("truck", truckService.getTruck(deletedMeasure.getTruckId()));
        return "deleteMeasure";
    }

    @GetMapping("measure/listOfOverloaded")
    public String getListOfOverloaded(@ModelAttribute("marker") MarkerOfFilter marker, Model model) {
        model.addAttribute("measureDTOList", measureService.getListOfOverloadedMeasureDTO());
        return "measureList";
    }

    @GetMapping("measure/listOfOverloaded/{id}")
    public String getListOfOverloadedAndByTruckId(@ModelAttribute("marker") MarkerOfFilter marker, @PathVariable("id") Long id, Model model) {
        List<MeasureDTO> listOfOverloadedAndById = measureService.getListOfOverloadedAndByTruckIdDTO(id);
        marker.setFiltered(true);
        marker.setId(id);
        model.addAttribute("measureDTOList", listOfOverloadedAndById);
        return "measureList";
    }

    @GetMapping("measure/listOfNotOverloaded")
    public String getListOfNotOverloaded(@ModelAttribute("marker") MarkerOfFilter marker, Model model) {
        model.addAttribute("measureDTOList", measureService.getListOfNotOverloadedMeasureDTO());
        return "measureList";
    }

    @GetMapping("measure/listOfNotOverloaded/{id}")
    public String getListOfNotOverloadedAndByTruckId(@ModelAttribute("marker") MarkerOfFilter marker, @PathVariable("id") Long id, Model model) {
        List<MeasureDTO> listOfOverloadedAndById = measureService.getListOfNotOverloadedAndByTruckIdDTO(id);
        marker.setFiltered(true);
        marker.setId(id);
        model.addAttribute("measureDTOList", listOfOverloadedAndById);
        return "measureList";
    }

}
