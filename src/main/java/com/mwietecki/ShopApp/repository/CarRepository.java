package com.mwietecki.ShopApp.repository;

import com.mwietecki.ShopApp.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository <Car, Long> {
}
