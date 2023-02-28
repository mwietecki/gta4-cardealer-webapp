package com.mwietecki.ShopApp.controller;

import com.mwietecki.ShopApp.model.Car;
import com.mwietecki.ShopApp.model.order.Order;
import com.mwietecki.ShopApp.repository.CarRepository;
import com.mwietecki.ShopApp.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final CarRepository carRepository;
    private final OrderRepository orderRepository;


    @Autowired
    public AdminController(CarRepository carRepository, OrderRepository orderRepository) {
        this.carRepository = carRepository;
        this.orderRepository = orderRepository;
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

    @GetMapping("/showorders")
    @ResponseBody
    public List<Order> showOrders() {
        return orderRepository.findAll();
    }
}
