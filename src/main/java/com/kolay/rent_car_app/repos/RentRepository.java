package com.kolay.rent_car_app.repos;

import com.kolay.rent_car_app.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RentRepository extends JpaRepository<Rent, Long> {
}
