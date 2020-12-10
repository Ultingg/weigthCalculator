package ru.isaykin.application.repositories;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.isaykin.application.model.Measure;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MeasureRepository extends CrudRepository<Measure, Long> {

    @Query("INSERT INTO measure (cargo_weight, complete_weight, date_of_measure," +
            "front_bar, front_weight, rear_bar, rear_weight, overloaded, truck_id)" +
            " VALUES(:cargoWeight, :completeWeight, :dateOfMeasure, :frontBar, :frontWeight," +
            ":rearBar, :rearWeight, :overloaded, :truckId)")
    @Modifying
    void create(@Param("cargoWeight") double cargoWeight,
                @Param("completeWeight") double completeWeight,
                @Param("dateOfMeasure") Timestamp dateOfMeasure,
                @Param("frontBar") double frontBar,
                @Param("frontWeight") double frontWeight,
                @Param("rearBar") double reaBar,
                @Param("rearWeight") double rearWeight,
                @Param("truckId") Long truckId,
                @Param("overloaded") boolean overloaded);

    Measure getById(Long id);

    Measure getByDateOfMeasure(LocalDateTime dateOfMeasure);

    @Query("SELECT * FROM measure")
    List<Measure> getAll();

    @Query("SELECT t.truck_number, m.date_of_measure, m.cargo_weight, m.front_weight, m.rear_weight, m.complete_weight, m.overloaded" +
            "FROM truck  as t" +
            " JOIN measure as m  " +
            " ON t.id = m.truck_id" +
            " where m.overloaded = :overloaded")
    @Modifying
    List<Measure> getListOfOverloadedMeasures(@Param("overloaded") boolean overloaded);

    @Query("SELECT t.truck_number, m.date_of_measure, m.cargo_weight, m.front_weight, m.rear_weight, m.complete_weight, m.overloaded" +
            "FROM truck  as t" +
            "JOIN measure as m  " +
            "ON t.id = m.truck_id" +
            "where t.id   = :id")
    @Modifying
    List<Measure> getListOfMeasuresByTruckId(@Param("id") Long id);

}
