package com.mwietecki.ShopApp.service;


import com.mwietecki.ShopApp.Cart;
import com.mwietecki.ShopApp.dto.OrderDto;
import com.mwietecki.ShopApp.mapper.OrderMapper;
import com.mwietecki.ShopApp.model.order.Order;
import com.mwietecki.ShopApp.model.order.OrderCar;
import com.mwietecki.ShopApp.repository.OrderCarRepository;
import com.mwietecki.ShopApp.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final Cart cart;
    private final OrderRepository orderRepository;
    private final OrderCarRepository orderCarRepository;

    @Autowired
    public OrderService(Cart cart, OrderRepository orderRepository, OrderCarRepository orderCarRepository) {
        this.cart = cart;
        this.orderRepository = orderRepository;
        this.orderCarRepository = orderCarRepository;
    }

    public void saveOrder(OrderDto orderDto) {
        Order order = OrderMapper.mapToOrder(orderDto);
        orderRepository.save(order);
        List<OrderCar> orderCars = OrderMapper.mapToOrderCarList(cart, order);
        orderCarRepository.saveAll(OrderMapper.mapToOrderCarList(cart, order));
        cart.clearCart();
    }
}
