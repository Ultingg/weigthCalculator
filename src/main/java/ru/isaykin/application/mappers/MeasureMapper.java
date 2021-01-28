package ru.isaykin.application.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.isaykin.application.DTO.MeasureDTO;
import ru.isaykin.application.model.Measure;
import ru.isaykin.application.model.Truck;

@Mapper
public interface MeasureMapper {

    MeasureMapper INSTANCE = Mappers.getMapper(MeasureMapper.class);


    @Mapping(source = "measure.id", target = "id")
    @Mapping(source = "measure.completeWeight", target = "completeWeight")
    @Mapping(source = "measure.cargoWeight", target = "cargoWeight")
    @Mapping(source = "measure.frontWeight", target = "frontWeight")
    @Mapping(source = "measure.rearWeight", target = "rearWeight")
    @Mapping(source = "measure.frontBar", target = "frontBar")
    @Mapping(source = "measure.rearBar", target = "rearBar")
    @Mapping(source = "measure.overloaded", target = "overloaded")
    @Mapping(source = "measure.frontOverloaded", target = "frontOverloaded")
    @Mapping(source = "measure.rearOverloaded", target = "rearOverloaded")
    @Mapping(source = "measure.completeOverloaded", target = "completeOverloaded")
    @Mapping(source = "measure.dateOfMeasure", target = "dateOfMeasure")
    @Mapping(source = "truck.truckNumber", target = "truckNumber")
    MeasureDTO fromMeasure(Measure measure, Truck truck);
}
