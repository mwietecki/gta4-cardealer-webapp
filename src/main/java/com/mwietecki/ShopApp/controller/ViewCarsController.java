package com.mwietecki.ShopApp.controller;

import com.mwietecki.ShopApp.Order;
import com.mwietecki.ShopApp.model.Car;
import com.mwietecki.ShopApp.repository.CarRepository;
import com.mwietecki.ShopApp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ViewCarsController {

    private final OrderService orderService;

    @Autowired
    public ViewCarsController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/viewcars")
    public String viewCars(Model model) {
        model.addAttribute("cars", orderService.getAllCars());
        return "viewcars";
    }

    @GetMapping("/viewcars/add/{carId}")
    public String addItemToOrder(@PathVariable("carId") Long carId, Model model) {

        orderService.addCarToOrder(carId);
        model.addAttribute("cars", orderService.getAllCars());
        return "viewcars";
    }
}
