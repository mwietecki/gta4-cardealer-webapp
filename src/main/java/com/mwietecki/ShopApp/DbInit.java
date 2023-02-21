package com.mwietecki.ShopApp;

import com.mwietecki.ShopApp.model.Car;
import com.mwietecki.ShopApp.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DbInit implements CommandLineRunner {

    private final CarRepository carRepository;

    @Autowired
    public DbInit(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void run(String... args) throws IOException{
        carRepository.saveAll(List.of(
                new Car("Banshee","Skora klima itp", new BigDecimal("110000"),"http://www.gta-universum.de/bilder/gta4/fahrzeuge/sportwagen/gross/banshee_01.png"),
                new Car("Turismo","Szybkie ladne", new BigDecimal("130000"),"http://www.gta-universum.de/bilder/gta4/fahrzeuge/sportwagen/gross/turismo_01.png")
        ));
    }
}
