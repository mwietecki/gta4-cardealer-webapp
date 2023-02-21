package com.mwietecki.ShopApp;

import com.mwietecki.ShopApp.model.Car;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
public class Order {
    private List<OrderCar> orderCars = new ArrayList<>();
    private int counter = 0;
    private BigDecimal sum = BigDecimal.ZERO;

    public void addCar(Car car) {
        boolean notFound = true;

        for (OrderCar oc : orderCars){
            if (oc.getCar().getId().equals(car.getId())){
                notFound = false;
                oc.increaseCounter();
                recalculatePriceAndCounter();
                break;
            }
        }

        if (notFound) {
            orderCars.add(new OrderCar(car));
            recalculatePriceAndCounter();
        }
    }

    public void removeCar(Car car) {
        for (OrderCar oc : orderCars){
            if (oc.getCar().getId().equals(car.getId())){
                oc.decreaseCounter();
                if (oc.hasZeroCars()) {
                    orderCars.remove(oc);
                }
                recalculatePriceAndCounter();
                break;
            }
        }
    }

    private void recalculatePriceAndCounter() {
        int tempCounter = 0;
        BigDecimal tempPrice = BigDecimal.ZERO;

        for (OrderCar oc : orderCars) {
            tempCounter += oc.getCounter();
            tempPrice = tempPrice.add(oc.getPrice());
        }
        this.counter = tempCounter;
        this.sum = tempPrice;
    }
}
