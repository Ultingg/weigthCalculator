package ru.isaykin.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.isaykin.application.logic.model.Truck;

import java.util.List;

@Repository
public interface TruckRepository extends CrudRepository<Truck, Long> {

    Truck getById(Long id);
//    @Modifying
//    @Query("INSERT INTO truck (first_wheel_weight, front_price, rear_price, truck_number, truck_weight) VALUES (:firstWheelWeight, :firstPrice, :rearPrice, :truckNumber, :truckWeight)")
//    void insert(@Param("first_wheel_weight") double firstWheelWeight,
//                @Param("frontPrice") double firstPrice,
//                @Param("rearPrice") double rearPrice,
//                @Param("truckNumber") String truckNumber,
//                @Param("truckWeight") double truckWeight);
}
