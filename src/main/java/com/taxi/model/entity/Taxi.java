package com.taxi.model.entity;

import java.util.Objects;

public class Taxi {
    private final Long idCar;
    private final String carNumber;
    private final String brand;
    private final String carType;

    public Taxi(Long idCar, String carNumber, String brand, String carType) {
        this.idCar = idCar;
        this.carNumber = carNumber;
        this.brand = brand;
        this.carType = carType;
    }

    public Long getIdCar() {
        return idCar;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public String getBrand() {
        return brand;
    }

    public String getCarType() {
        return carType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Taxi taxi = (Taxi) o;
        return idCar == taxi.idCar &&
                Objects.equals(carNumber, taxi.carNumber) &&
                Objects.equals(brand, taxi.brand) &&
                Objects.equals(carType, taxi.carType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCar, carNumber, brand, carType);
    }

    @Override
    public String toString() {
        return "Taxi{" +
                "idCar=" + idCar +
                ", carNumber='" + carNumber + '\'' +
                ", brand='" + brand + '\'' +
                ", carType='" + carType + '\'' +
                '}';
    }
}
