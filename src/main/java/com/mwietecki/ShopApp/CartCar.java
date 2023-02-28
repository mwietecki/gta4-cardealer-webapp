package com.mwietecki.ShopApp;

import com.mwietecki.ShopApp.model.Car;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CartCar {
    private Car car;
    private int counter;
    private BigDecimal price;

    public CartCar(Car car) {
        this.car = car;
        this.counter = 1;
        this.price = car.getPrice();
    }

    public void increaseCounter() {
        counter++;
        recarculate();
    }

    public void decreaseCounter() {
        if (counter > 0){
            counter--;
            recarculate();
        }
    }

    public boolean hasZeroCars() {
        return counter == 0;
    }

    private void recarculate(){
        price = car.getPrice().multiply(new BigDecimal(counter));
    }

}
