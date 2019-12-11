package com.taxi.model.mapper;

import com.taxi.model.domain.Car;
import com.taxi.model.entity.CarEntity;

public class CarMapper {

    public Car carEntityToCar(CarEntity carEntity) {
        if (carEntity == null) {
            return null;
        }
        Car car = new Car();
        car.setIdCar(carEntity.getIdCar());
        car.setCarNumber(carEntity.getCarNumber());
        car.setBrand(carEntity.getBrand());
        car.setColor(carEntity.getColor());
        car.setCarType(carEntity.getCarType());
        return car;
    }

    public CarEntity carToCarEntity(Car car) {
        if (car == null) {
            return null;
        }
        CarEntity carEntity = new CarEntity();
        carEntity.setIdCar(car.getIdCar());
        carEntity.setCarNumber(car.getCarNumber());
        carEntity.setBrand(car.getBrand());
        carEntity.setCarType(car.getCarType());
        carEntity.setColor(car.getColor());

        return carEntity;
    }
}
