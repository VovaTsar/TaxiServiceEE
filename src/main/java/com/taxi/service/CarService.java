package com.taxi.service;

import com.taxi.model.entity.Car;

import java.util.List;

public interface CarService {
    Car getCarById(Integer id);

    List<Car> getAllCars();

    boolean findCarByIdAndCarType(Integer idCar, String type);
}
