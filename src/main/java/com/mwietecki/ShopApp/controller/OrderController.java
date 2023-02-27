package com.mwietecki.ShopApp.controller;

import com.mwietecki.ShopApp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orderview")
    public String showOrder() {
        return "orderView";
    }

    @GetMapping("/remove/{carId}")
    public String removeCarFromOrder(@PathVariable("carId") Long carId) {
        orderService.removeCar(carId);
        return "orderView";
    }
}
