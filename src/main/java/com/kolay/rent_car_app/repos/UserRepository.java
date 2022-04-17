package com.kolay.rent_car_app.repos;

import com.kolay.rent_car_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
