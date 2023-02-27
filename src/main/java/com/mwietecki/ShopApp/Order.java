package com.mwietecki.ShopApp;

import com.mwietecki.ShopApp.model.Car;
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
public class Order {
    private List<OrderCar> orderCars = new ArrayList<>();
    private int counter = 0;
    private BigDecimal sum = BigDecimal.ZERO;

    public void addCar(Car car) {
        getOrderCarByCar(car).ifPresentOrElse(
                OrderCar::increaseCounter,
                () -> orderCars.add(new OrderCar(car))
        );
        recalculatePriceAndCounter();
    }

    public void removeCar(Car car) {
        Optional<OrderCar> oOrderCar = getOrderCarByCar(car);
        if (oOrderCar.isPresent()){
            OrderCar orderCar = oOrderCar.get();
            orderCar.decreaseCounter();
            if (orderCar.hasZeroCars()) {
                removeAllCars(car);
            }
        }
        recalculatePriceAndCounter();
    }

    public void removeAllCars(Car car) {
        orderCars.removeIf(i -> i.equals(car));
    }

    private void recalculatePriceAndCounter() {
        sum = orderCars.stream().map(OrderCar::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        counter = orderCars.stream().mapToInt(OrderCar::getCounter)
                .reduce(0, Integer::sum);
    }



    private Optional<OrderCar> getOrderCarByCar(Car car) {
        return orderCars.stream()
                .filter(i -> i.getCar().getId().equals(car.getId()))
                .findFirst();
    }
}
