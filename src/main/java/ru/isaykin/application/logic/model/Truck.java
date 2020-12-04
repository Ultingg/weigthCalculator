package ru.isaykin.application.logic.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity(name = "truck")
public class Truck {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
                 double rearBar) {
        this.truckNumber = truckNumber;
        this.frontPrice = frontPrice;
        this.rearPrice = rearBar;
        this.firstWheelWeight = firstWheelWeight;
        this.truckWeight = truckWeight;
    }

    public String toString() {
        return truckNumber;
    }



}
