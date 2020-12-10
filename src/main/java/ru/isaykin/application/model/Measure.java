package ru.isaykin.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@Data
@Table("measure")
@AllArgsConstructor
public class Measure {


    @Id
    private Long id;

    private Long truckId;
    private double frontWeight;
    private double rearWeight;
    private double completeWeight;
    private double cargoWeight;
    private LocalDateTime dateOfMeasure;
    private double frontBar;
    private double rearBar;
    @Nullable
    private boolean overloaded;

    public Measure() {
    }

    public void measureSet(Long truckId, double frontWeight, double rearWeight, double completeWeight, double cargoWeight, double frontBar, double rearBar) {
        this.truckId = truckId;
        this.frontWeight = frontWeight;
        this.rearWeight = rearWeight;
        this.completeWeight = completeWeight;
        this.cargoWeight = cargoWeight;
        this.dateOfMeasure = LocalDateTime.now();
        this.overloaded = isOverloaded(frontWeight, rearWeight, completeWeight);
        this.frontBar = frontBar;
        this.rearBar = rearBar;

    }

    public void calcWeights(Truck truck, double frontBar, double rearBar) {
        frontWeight = (frontBar * 10 * truck.getFrontPrice()) - truck.getFirstWheelWeight();
        rearWeight = rearBar * 10 * truck.getRearPrice();
        completeWeight = frontWeight + rearWeight + truck.getFirstWheelWeight();
        cargoWeight = completeWeight - truck.getTruckWeight();

        measureSet(truck.getId(), frontWeight,
                rearWeight, completeWeight,
                cargoWeight, frontBar, rearBar);

    }

    public boolean isOverloaded(double frontWeight, double rearWeight, double completeWeight) {
        double completeWeightLimit = 44000;
        double frontWeightLimit = 15461;
        double rearWeightLimit = 23192;
        return frontWeight > frontWeightLimit || rearWeight > rearWeightLimit || completeWeight > completeWeightLimit;
    }

//    public String toString() {
//        return String.format(
//                " Квитанция взвешивания \n рег. номер ТС: %s \n Дата взвешивания: %tF \n" +
//                        " Полная масса: %.2f \n Масса груза:  %.2f\n Нагрузка на ведущую ось: %.2f \n" +
//                        " Нагрузка на заднюю ось прицепа: %.2f\n",
//                truckNumber, dateOfMeasure, completeWeight, cargoWeight, frontWeight, rearWeight);
//    }
}
