package ru.isaykin.application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Data
@Slf4j
@Builder
@Table("MEASURE")
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
    @Min(value = 1, message = "Давление не может быть меньше чем 1 бар")
    private double frontBar;
    @Min(value = 1, message = "Давление не может быть меньше чем 1 бар")
    private double rearBar;
    @Nullable
    private boolean overloaded;
    @Nullable
    private boolean frontOverloaded;
    @Nullable
    private boolean rearOverloaded;
    @Nullable
    private boolean completeOverloaded;


    public Measure() {
    }

    public void measureSet(Long truckId, double frontWeight, double rearWeight, double completeWeight, double cargoWeight, double frontBar, double rearBar) {
        this.truckId = truckId;
        this.frontWeight = frontWeight;
        this.rearWeight = rearWeight;
        this.completeWeight = completeWeight;
        this.cargoWeight = cargoWeight;
        this.dateOfMeasure = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        this.overloaded = calcOverloaded(frontWeight, rearWeight, completeWeight);
        this.frontBar = frontBar;
        this.rearBar = rearBar;
        this.frontOverloaded = calcFrontOverloaded();
        this.rearOverloaded = calcRearOverloaded();
        this.completeOverloaded = calcCompleteOverloaded();
    }


    public void calcWeights(Truck truck, double frontBar, double rearBar) {
        if (frontBar <= 0.99 || rearBar <= 0.99 || truck == null) {
            throw new NullPointerException();
        }
        frontWeight = (frontBar * 10 * truck.getFrontPrice()) - truck.getFirstWheelWeight();
        rearWeight = rearBar * 10 * truck.getRearPrice();
        completeWeight = frontWeight + rearWeight + truck.getFirstWheelWeight();
        cargoWeight = completeWeight - truck.getTruckWeight();

        measureSet(truck.getId(), frontWeight,
                rearWeight, completeWeight,
                cargoWeight, frontBar, rearBar);

    }

    private boolean calcOverloaded(double frontWeight, double rearWeight, double completeWeight) {
        double completeWeightLimit = 44000;
        double frontWeightLimit = 15461;
        double rearWeightLimit = 23192;
        return frontWeight > frontWeightLimit || rearWeight > rearWeightLimit || completeWeight > completeWeightLimit;
    }

    private boolean calcFrontOverloaded() {
        double frontWeightLimit = 15461;
        return this.frontWeight > frontWeightLimit;
    }

    private boolean calcRearOverloaded() {
        double rearWeightLimit = 23192;
        return this.rearWeight > rearWeightLimit;
    }

    private boolean calcCompleteOverloaded() {
        double completeWeightLimit = 44000;
        return this.completeWeight > completeWeightLimit;
    }

}
