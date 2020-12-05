package ru.isaykin.application.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data

@Table("truck")
@AllArgsConstructor
public class Truck {


    @Id
    private Long id;

    private String truckNumber;
    private double frontPrice;
    private double rearPrice;
    private double firstWheelWeight;
    private double truckWeight;

    public Truck() {
    }

    public Truck(String truckNumber, double truckWeight,
                 double firstWheelWeight, double frontPrice,
                 double rearPrice) {
        this.truckNumber = truckNumber;
        this.frontPrice = frontPrice;
        this.rearPrice = rearPrice;
        this.firstWheelWeight = firstWheelWeight;
        this.truckWeight = truckWeight;
    }

    public String toString() {
        return truckNumber;
    }



}
