package com.mwietecki.ShopApp.controller;

import com.mwietecki.ShopApp.Order;
import com.mwietecki.ShopApp.model.Car;
import com.mwietecki.ShopApp.repository.CarRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    private final Order order;
    private final CarRepository carRepository;
    @Autowired
    public HomeController(Order order, CarRepository carRepository) {
        this.order = order;
        this.carRepository = carRepository;
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/viewcars")
    public String viewCars(Model model) {
        model.addAttribute("cars", carRepository.findAll());
        return "viewcars";
    }

    @GetMapping("/add/{carId}")
    public String addItemToOrder(@PathVariable("carId") Long carId, Model model) {
        Optional<Car> oCar = carRepository.findById(carId);
        if (oCar.isPresent()){
            Car car = oCar.get();
            order.addCar(car);
        }
        model.addAttribute("cars", carRepository.findAll());
        return "viewcars";
    }
}
