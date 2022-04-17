package com.kolay.rent_car_app.controller;

import com.kolay.rent_car_app.model.Car;
import com.kolay.rent_car_app.model.Rent;
import com.kolay.rent_car_app.model.User;
import com.kolay.rent_car_app.service.CarService;
import com.kolay.rent_car_app.service.RentService;
import com.kolay.rent_car_app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;


@Controller
public class HomeController {

    final UserService userService;
    final CarService carService;
    final RentService rentService;

    public HomeController(UserService userService, CarService carService, RentService rentService) {
        this.userService = userService;
        this.carService = carService;
        this.rentService = rentService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/rents")
    public String reservations(Model model, HttpSession session) {
        User user = userService.get(10000L);
        session.setAttribute("user", user);
        Rent rent = new Rent();
        model.addAttribute("rent", rent);
        List<Car> cars = carService.findAll();
        model.addAttribute("cars", cars);
        return "rents";
    }

    @PostMapping("/rents-submit")
    public String rentsSubmit(@ModelAttribute Rent rent,
                                     @SessionAttribute("user") User user) {

        // Save to DB after updating
        assert user != null;
        rent.setUser(user);
        rentService.create(rent);
        Set<Rent> userRents = user.getUserRents();
        userRents.add(rent);
        user.setUserRents(userRents);
        userService.update(user.getId(), user);
        return "redirect:/rents";
    }
}
