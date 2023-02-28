package com.mwietecki.ShopApp.service;

import com.mwietecki.ShopApp.Cart;
import com.mwietecki.ShopApp.model.Car;
import com.mwietecki.ShopApp.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final Cart cart;
    private final CarRepository carRepository;
    @Autowired
    public CartService(Cart cart, CarRepository carRepository) {
        this.cart = cart;
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public void addCarToOrder(Long carId) {
        Optional<Car> oCar = carRepository.findById(carId);
        if (oCar.isPresent()){
            Car car = oCar.get();
            cart.addCar(car);
        }
    }

    public void removeCar(Long carId) {
        Optional<Car> oCar = carRepository.findById(carId);
        if (oCar.isPresent()) {
            Car car = oCar.get();
            cart.removeCar(car);
        }
    }
}
