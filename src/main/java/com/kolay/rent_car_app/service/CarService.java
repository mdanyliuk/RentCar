package com.kolay.rent_car_app.service;

import com.kolay.rent_car_app.model.Car;
import com.kolay.rent_car_app.repos.CarRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(final CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Car get(final Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final Car car) {
        return carRepository.save(car).getId();
    }

    public void update(final Long id, final Car car) {
        final Car existingCar = carRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        carRepository.save(car);
    }

    public void delete(final Long id) {
        carRepository.deleteById(id);
    }

}
