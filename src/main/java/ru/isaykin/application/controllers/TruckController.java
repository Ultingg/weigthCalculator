package ru.isaykin.application.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.isaykin.application.model.Truck;
import ru.isaykin.application.services.TruckService;

import javax.validation.Valid;
import java.io.*;
import java.util.List;


@SuppressWarnings("SpringMVCViewInspection")
@Slf4j
@Controller
@RequestMapping("/trucks")
public class TruckController {

    private final TruckService truckService;

    public TruckController(TruckService truckService) {
        this.truckService = truckService;
    }

    @ModelAttribute("truckList")
    public List<Truck> getTruckListUtil() {
        return truckService.getAll2();
    }

    @GetMapping("new")
    public String newTruck(@ModelAttribute("truck") Truck truck) {
        return "newTruck";
    }

    @PostMapping("creation")
    public String creatingTruck(@ModelAttribute("truck")
                                @Valid Truck truck,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "newTruck";
        }

        truckService.addTruck(truck);
        return "redirect:/trucks/list";
    }

    @GetMapping("list")
    public String getListOfTrucks() {
        return "truckList";
    }

    @GetMapping("{id}")
    public String showTruckInfo(Model model,
                                @PathVariable Long id) {
        model.addAttribute("truck", truckService.getTruck(id));
        return "truckCard";
    }

    @DeleteMapping("{id}")
    public String deleteTruck(@PathVariable Long id) {
        truckService.deleteById(id);
        return "redirect:/trucks/list";
    }

    @PutMapping("{id}")
    public String updateTruckById(@ModelAttribute("truck")
                                  @Valid Truck truck,
                                  BindingResult bindingResult,
                                  @PathVariable Long id) {
        if (bindingResult.hasErrors()) {
            return "truckCard";
        }

        truckService.updateById(id, truck);
        return "redirect:/trucks/{id}";
    }

    @ModelAttribute("greetingsMessage")
    public String showGreetingMessage() {
        return "Калькулятор весовых нагрузок";
    }


}
