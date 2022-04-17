package com.kolay.rent_car_app.service;

import com.kolay.rent_car_app.model.Rent;
import com.kolay.rent_car_app.repos.CarRepository;
import com.kolay.rent_car_app.repos.RentRepository;
import com.kolay.rent_car_app.repos.UserRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class RentService {

    private final RentRepository rentRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    public RentService(final RentRepository rentRepository, final CarRepository carRepository,
            final UserRepository userRepository) {
        this.rentRepository = rentRepository;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    public List<Rent> findAll() {
        return rentRepository.findAll();
    }

    public Rent get(final Long id) {
        return rentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final Rent rent) {
        return rentRepository.save(rent).getId();
    }

    public void update(final Long id, final Rent rent) {
        final Rent existingRent = rentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        rentRepository.save(rent);
    }

    public void delete(final Long id) {
        rentRepository.deleteById(id);
    }

}
