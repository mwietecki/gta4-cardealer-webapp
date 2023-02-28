package com.mwietecki.ShopApp.model.order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class OrderCar {

    @Id
    @GeneratedValue
    private Long OrderCarId;
    private Long orderId;
    private Long carId;
    private int amount;

    public OrderCar(Long orderId, Long carId, int amount) {
        this.orderId = orderId;
        this.carId = carId;
        this.amount = amount;
    }
}
