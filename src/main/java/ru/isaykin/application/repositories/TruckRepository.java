package ru.isaykin.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.isaykin.application.logic.model.Truck;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Long> {

}
