package com.kolay.rent_car_app.repos;

import com.kolay.rent_car_app.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CarRepository extends JpaRepository<Car, Long> {
}
