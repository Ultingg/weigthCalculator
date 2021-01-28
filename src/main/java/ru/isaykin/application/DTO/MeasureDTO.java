package ru.isaykin.application.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Data
@Slf4j
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
}
