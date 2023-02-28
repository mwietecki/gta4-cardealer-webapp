package com.mwietecki.ShopApp.controller;

import com.mwietecki.ShopApp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewCarsController {

    private final CartService cartService;

    @Autowired
    public ViewCarsController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/viewcars")
    public String viewCars(Model model) {
        model.addAttribute("cars", cartService.getAllCars());
        return "viewcars";
    }

    @GetMapping("/viewcars/add/{carId}")
    public String addItemToOrder(@PathVariable("carId") Long carId, Model model) {

        cartService.addCarToOrder(carId);
        model.addAttribute("cars", cartService.getAllCars());
        return "viewcars";
    }
}
