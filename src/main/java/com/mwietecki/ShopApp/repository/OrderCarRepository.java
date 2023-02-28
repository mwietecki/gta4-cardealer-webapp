package com.mwietecki.ShopApp.repository;

import com.mwietecki.ShopApp.model.order.OrderCar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderCarRepository extends JpaRepository<OrderCar, Long> {

}
