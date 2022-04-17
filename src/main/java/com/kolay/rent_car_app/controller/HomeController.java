package com.kolay.rent_car_app.controller;

import com.kolay.rent_car_app.model.User;
import com.kolay.rent_car_app.service.CarService;
import com.kolay.rent_car_app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HomeController {

    final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/rents")
    public String reservations(Model model) {
        User user = userService.get(10000L);
        model.addAttribute("user", user);

        return "rents";
    }
}
