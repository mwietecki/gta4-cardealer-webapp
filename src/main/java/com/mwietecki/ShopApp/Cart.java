package com.mwietecki.ShopApp;

import com.mwietecki.ShopApp.dto.OrderDto;
import com.mwietecki.ShopApp.mapper.OrderMapper;
import com.mwietecki.ShopApp.model.Car;
import com.mwietecki.ShopApp.model.order.Order;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
public class Cart {
    private List<CartCar> cartCars = new ArrayList<>();
    private int counter = 0;
    private BigDecimal sum = BigDecimal.ZERO;

    public void addCar(Car car) {
        getOrderCarByCar(car).ifPresentOrElse(
                CartCar::increaseCounter,
                () -> cartCars.add(new CartCar(car))
        );
        recalculatePriceAndCounter();
    }

    public void removeCar(Car car) {
        Optional<CartCar> oOrderCar = getOrderCarByCar(car);
        if (oOrderCar.isPresent()){
            CartCar cartCar = oOrderCar.get();
            cartCar.decreaseCounter();
            if (cartCar.hasZeroCars()) {
                removeAllCars(car);
            }
        }
        recalculatePriceAndCounter();
    }

    public void removeAllCars(Car car) {
        cartCars.removeIf(i -> i.equals(car));
    }

    private void recalculatePriceAndCounter() {
        sum = cartCars.stream().map(CartCar::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        counter = cartCars.stream().mapToInt(CartCar::getCounter)
                .reduce(0, Integer::sum);
    }



    private Optional<CartCar> getOrderCarByCar(Car car) {
        return cartCars.stream()
                .filter(i -> i.getCar().getId().equals(car.getId()))
                .findFirst();
    }

    public void clearCart() {
        cartCars.clear();
        counter = 0;
        sum = BigDecimal.ZERO;
    }
}
