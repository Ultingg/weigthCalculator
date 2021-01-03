package ru.isaykin.application.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@NoArgsConstructor
public class MeasureDTO extends Measure {
    private String truckNumber;

    public MeasureDTO(Measure measure, Truck truck) {
        setId(measure.getId());
        truckNumber = truck.getTruckNumber();
        setDateOfMeasure(measure.getDateOfMeasure());
        setFrontBar(measure.getFrontBar());
        setRearBar(measure.getRearBar());
        setCargoWeight(measure.getCargoWeight());
        setCompleteWeight(measure.getCompleteWeight());
        setFrontWeight(measure.getFrontWeight());
        setRearWeight(measure.getRearWeight());
        setFrontOverloaded(measure.isFrontOverloaded());
        setRearOverloaded(measure.isRearOverloaded());
        setCompleteOverloaded(measure.isCompleteOverloaded());
        setOverloaded(measure.isOverloaded());
        setTruckId(measure.getTruckId());
    }

}
