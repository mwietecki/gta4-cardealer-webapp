package com.mwietecki.ShopApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {


    @GetMapping("/order")
    public String showOrder() {
        return "orderView";
    }
}
