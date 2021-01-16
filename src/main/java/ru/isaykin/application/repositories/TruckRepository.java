package ru.isaykin.application.repositories;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.isaykin.application.model.Truck;

import java.util.List;

@Repository
public interface TruckRepository extends CrudRepository<Truck, Long> {

    @Query("SELECT truck.ID AS ID, truck.REAR_PRICE AS REAR_PRICE, " +
            "truck.FRONT_PRICE AS FRONT_PRICE, truck.TRUCK_WEIGHT AS TRUCK_WEIGHT, " +
            "truck.TRUCK_NUMBER AS TRUCK_NUMBER, truck.FIRST_WHEEL_WEIGHT AS FIRST_WHEEL_WEIGHT " +
            "FROM truck WHERE truck.ID =:id;")
    Truck getById(@Param("id")Long id);

    @Query("SELECT * FROM truck")
    List<Truck> getAll();

    @Modifying
    @Query("INSERT INTO truck (first_wheel_weight, front_price, rear_price, truck_number, truck_weight) VALUES (:firstWheelWeight, :frontPrice, :rearPrice, :truckNumber, :truckWeight)")
    void create(@Param("firstWheelWeight") double firstWheelWeight,
                @Param("frontPrice") double firstPrice,
                @Param("rearPrice") double rearPrice,
                @Param("truckNumber") String truckNumber,
                @Param("truckWeight") double truckWeight);

    Truck getByTruckNumber(String TruckNumber);

    void deleteById(Long id);
}
