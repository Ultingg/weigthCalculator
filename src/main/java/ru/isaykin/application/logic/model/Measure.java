package ru.isaykin.application.logic.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;
import java.util.Date;

@Data
@Entity(name = "measure")
public class Measure {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String truckNumber;
    private double frontWeight;
    private double rearWeight;
    private double completeWeight;
    private double truckWeight;
    private double cargoWeight;
    private double frontBar;
    private double rearBar;
    private Date dateOfMeasure;

    public Measure() {
    }

    public void measureSet(String truckNumber, double frontWeight, double rearWeight, double completeWeight, double truckWeight, double cargoWeight) {
        this.truckNumber = truckNumber;
        this.frontWeight = frontWeight;
        this.rearWeight = rearWeight;
        this.completeWeight = completeWeight;
        this.truckWeight = truckWeight;
        this.cargoWeight = cargoWeight;
        this.dateOfMeasure = Date.from(Instant.now());
    }

    public void calcWeights(Truck truck, double frontBar, double rearBar) {
        frontWeight = (frontBar * 10 * truck.getFrontPrice()) - truck.getFirstWheelWeight();
        rearWeight = rearBar * 10 * truck.getRearPrice();
        completeWeight = frontWeight + rearWeight + truck.getFirstWheelWeight();
        cargoWeight = completeWeight - truck.getTruckWeight();
        measureSet(truck.getTruckNumber(), frontWeight,
                rearWeight, completeWeight,
                truck.getTruckWeight(), cargoWeight);
    }

    public String toString() {
        return String.format(
                " Квитанция взвешивания \n рег. номер ТС: %s \n Дата взвешивания: %tF \n" +
                        " Полная масса: %.2f \n Масса груза:  %.2f\n Нагрузка на ведущую ось: %.2f \n" +
                        " Нагрузка на заднюю ось прицепа: %.2f\n",
                truckNumber, dateOfMeasure, completeWeight, cargoWeight, frontWeight, rearWeight);
    }
}
