package com.mwietecki.ShopApp.service;

import com.mwietecki.ShopApp.Order;
import com.mwietecki.ShopApp.model.Car;
import com.mwietecki.ShopApp.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final Order order;
    private final CarRepository carRepository;
    @Autowired
    public OrderService(Order order, CarRepository carRepository) {
        this.order = order;
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public void addCarToOrder(Long carId) {
        Optional<Car> oCar = carRepository.findById(carId);
        if (oCar.isPresent()){
            Car car = oCar.get();
            order.addCar(car);
        }
    }

    public void removeCar(Long carId) {
        Optional<Car> oCar = carRepository.findById(carId);
        if (oCar.isPresent()) {
            Car car = oCar.get();
            order.removeCar(car);
        }
    }
}
