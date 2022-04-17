package com.kolay.rent_car_app;

import com.kolay.rent_car_app.model.Car;
import com.kolay.rent_car_app.model.Rent;
import com.kolay.rent_car_app.model.User;
import com.kolay.rent_car_app.repos.CarRepository;
import com.kolay.rent_car_app.repos.RentRepository;
import com.kolay.rent_car_app.repos.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;


@SpringBootApplication
public class RentCarAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentCarAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository,
                                      CarRepository carRepository,
                                      RentRepository rentRepository) {
        return (args) -> {
            User user = userRepository.save(new User("John Doe",
                    "JohnDoe",
                    bCryptPasswordEncoder().encode("111")));
            userRepository.save(new User("Bill Smith",
                    "BillSmith",
                    bCryptPasswordEncoder().encode("111")));
            Car car = Car.builder()
                    .name("Hyundai Accent")
                    .price(50.0)
                    .build();
            carRepository.save(car);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Rent rent = Rent.builder()
                    .car(car)
                    .startDate(localDate)
                    .endDate(localDate)
                    .startTime(LocalTime.of(10, 00))
                    .endTime(LocalTime.of(14, 00))
                    .user(user)
                    .build();

            rentRepository.save(rent);
        };
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
