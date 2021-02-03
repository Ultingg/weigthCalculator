package ru.isaykin.application.repositories;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.isaykin.application.model.Truck;

import java.util.List;

@Component
@Repository
public interface TruckRepository extends CrudRepository<Truck, Long> {

    Truck getById(Long id);

    @Query("SELECT * FROM truck")
    List<Truck> getAll();

    @Modifying
    @Query("INSERT INTO truck (first_wheel_weight, front_price, rear_price, truck_number, truck_weight) VALUES (:firstWheelWeight, :frontPrice, :rearPrice, :truckNumber, :truckWeight)")
    void create(@Param("firstWheelWeight") double firstWheelWeight,
                @Param("frontPrice") double frontPrice,
                @Param("rearPrice") double rearPrice,
                @Param("truckNumber") String truckNumber,
                @Param("truckWeight") double truckWeight);


    void deleteById(Long id);
}
