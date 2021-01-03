package ru.isaykin.application.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.*;
import java.util.Set;

@Data
@Slf4j
@Table("truck")
@AllArgsConstructor

public class Truck {


    @Id
    private Long id;
    @NotEmpty(message = "Рег. номер не может быть пустым")
    @Size(max = 18, min = 6, message = "Длина рег. номера от 6 до 18 символов")
    private String truckNumber;
    @DecimalMin(value = "0.1", message = "Значение должно быть больше чем ноль")
    @NumberFormat
    private double frontPrice;
    @Min(value = 0, message = "Значение должно быть больше чем ноль")
    private double rearPrice;
    @Min(value = 0, message = "Значение должно быть больше чем ноль")
    private double firstWheelWeight;
    @Min(value = 0, message = "Значение должно быть больше чем ноль")
    private double truckWeight;
//    private Set<Measure> set;

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
//        this.set = measure;
    }

    public String toString() {
        return truckNumber;
    }


}
