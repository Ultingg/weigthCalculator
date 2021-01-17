package ru.isaykin.application.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Slf4j
@Builder
@AllArgsConstructor
public class Truck {


    @Id
    private Long id;

    @NotEmpty(message = "Рег. номер не может быть пустым")
    @Size(max = 18, min = 6, message = "Длина рег. номера от 6 до 18 символов")
    private String truckNumber;

    @NumberFormat
    @DecimalMin(value = "0.1", message = "Значение должно быть больше чем ноль")
    private double frontPrice;

    @NumberFormat
    @DecimalMin(value = "0.1", message = "Значение должно быть больше чем ноль")
    private double rearPrice;

    @Min(value = 0, message = "Значение должно быть больше чем ноль")
    private double firstWheelWeight;

    @Min(value = 0, message = "Значение должно быть больше чем ноль")
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

}
