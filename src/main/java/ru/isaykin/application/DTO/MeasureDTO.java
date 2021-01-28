package ru.isaykin.application.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeasureDTO {
    private Long id;
    private String truckNumber;
    private double frontWeight;
    private double rearWeight;
    private double completeWeight;
    private double cargoWeight;
    private LocalDateTime dateOfMeasure;
    private double frontBar;
    private double rearBar;
    private boolean overloaded;
    private boolean frontOverloaded;
    private boolean rearOverloaded;
    private boolean completeOverloaded;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        MeasureDTO that = (MeasureDTO) o;
//        return Double.compare(that.frontWeight, frontWeight) == 0 &&
//                Double.compare(that.rearWeight, rearWeight) == 0 &&
//                Double.compare(that.completeWeight, completeWeight) == 0 &&
//                Double.compare(that.cargoWeight, cargoWeight) == 0 &&
//                Double.compare(that.frontBar, frontBar) == 0 &&
//                Double.compare(that.rearBar, rearBar) == 0 &&
//                id.equals(that.id) &&
//                dateOfMeasure.equals(that.dateOfMeasure);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, frontWeight, rearWeight, completeWeight, cargoWeight, dateOfMeasure, frontBar, rearBar);
//    }


//
//    public MeasureDTO(Measure measure, Truck truck) {
//        setId(measure.getId());
//        truckNumber = truck.getTruckNumber();
//        setDateOfMeasure(measure.getDateOfMeasure());
//        setFrontBar(measure.getFrontBar());
//        setRearBar(measure.getRearBar());
//        setCargoWeight(measure.getCargoWeight());
//        setCompleteWeight(measure.getCompleteWeight());
//        setFrontWeight(measure.getFrontWeight());
//        setRearWeight(measure.getRearWeight());
//        setFrontOverloaded(measure.isFrontOverloaded());
//        setRearOverloaded(measure.isRearOverloaded());
//        setCompleteOverloaded(measure.isCompleteOverloaded());
//        setOverloaded(measure.isOverloaded());
//        setTruckId(measure.getTruckId());
//    }

//    public String getTruckNumber() {
//        return truckNumber;
//    }
//
//    public void setTruckNumber(String truckNumber) {
//        this.truckNumber = truckNumber;
//    }
}
