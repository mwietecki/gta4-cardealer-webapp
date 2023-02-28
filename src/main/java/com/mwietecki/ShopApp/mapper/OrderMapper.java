package com.mwietecki.ShopApp.mapper;

import com.mwietecki.ShopApp.Cart;
import com.mwietecki.ShopApp.CartCar;
import com.mwietecki.ShopApp.dto.OrderDto;
import com.mwietecki.ShopApp.model.order.Order;
import com.mwietecki.ShopApp.model.order.OrderCar;
import org.aspectj.weaver.ast.Or;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public static Order mapToOrder(OrderDto orderDto) {
        return Order.builder()
                .firstName(orderDto.getFirstName())
                .lastName(orderDto.getLastName())
                .address(orderDto.getAddress())
                .postCode(orderDto.getPostCode())
                .city(orderDto.getCity())
                .created(LocalDateTime.now())
                .build();
    }

    public static List<OrderCar> mapToOrderCarList(Cart cart, Order order) {
        List<OrderCar> orderCars = new ArrayList<>();
        for (CartCar cc : cart.getCartCars()){
            orderCars.add(new OrderCar(order.getOrderId(), cc.getCar().getId(),cc.getCounter()));
        }
        return orderCars;
    }
}
