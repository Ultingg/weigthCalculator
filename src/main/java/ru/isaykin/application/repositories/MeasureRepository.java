package ru.isaykin.application.repositories;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.isaykin.application.model.Measure;

import java.sql.Timestamp;
import java.util.List;

@SuppressWarnings("SpringDataRepositoryMethodParametersInspection")
@Repository
public interface MeasureRepository extends CrudRepository<Measure, Long> {

    @Query("INSERT INTO measure (cargo_weight, complete_weight, date_of_measure," +
            "front_bar, front_weight, rear_bar, rear_weight, overloaded, truck_id," +
            " front_overloaded, rear_overloaded, complete_overloaded)" +
            " VALUES(:cargoWeight, :completeWeight, :dateOfMeasure, :frontBar, :frontWeight," +
            ":rearBar, :rearWeight, :overloaded, :truckId, :frontOverloaded, :rearOverloaded, :completeOverloaded)")
    @Modifying
    void create(@Param("cargoWeight") double cargoWeight,
                @Param("completeWeight") double completeWeight,
                @Param("dateOfMeasure") Timestamp dateOfMeasure,
                @Param("frontBar") double frontBar,
                @Param("frontWeight") double frontWeight,
                @Param("rearBar") double reaBar,
                @Param("rearWeight") double rearWeight,
                @Param("truckId") Long truckId,
                @Param("overloaded") boolean overloaded,
                @Param("frontOverloaded") boolean frontOverloaded,
                @Param("rearOverloaded") boolean rearOverloaded,
                @Param("completeOverloaded") boolean completeOverloaded);

    Measure getById(Long id);

    @SuppressWarnings("SpringDataRepositoryMethodParametersInspection")
    Measure getByDateOfMeasure(Timestamp dateOfMeasure);

    @Query("SELECT * FROM measure")
    List<Measure> getAll();


    List<Measure> getMeasureByOverloaded(boolean overloaded);
}
