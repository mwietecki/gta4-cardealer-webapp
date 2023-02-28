package com.mwietecki.ShopApp.controller;

import com.mwietecki.ShopApp.dto.OrderDto;
import com.mwietecki.ShopApp.service.CartService;
import com.mwietecki.ShopApp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final CartService cartService;
    private final OrderService orderService;


    @Autowired
    public OrderController(CartService cartService, OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @GetMapping("/orderview")
    public String showOrder() {
        return "orderView";
    }

    @GetMapping("/remove/{carId}")
    public String removeCarFromOrder(@PathVariable("carId") Long carId) {
        cartService.removeCar(carId);
        return "orderView";
    }

    @PostMapping("/saveorder")
    public String saveOrder(OrderDto orderDto) {
        orderService.saveOrder(orderDto);
        return "redirect:/viewcars";
     }
}
