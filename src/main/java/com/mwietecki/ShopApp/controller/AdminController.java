package com.mwietecki.ShopApp.controller;

import com.mwietecki.ShopApp.model.Car;
import com.mwietecki.ShopApp.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final CarRepository carRepository;
    @Autowired
    public AdminController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping
    private String adminPage() {
        return "adminview/addcar";
    }

    @PostMapping
    private String addCar(Car car) {
        carRepository.save(car);
        return "redirect:/";
    }
}
