package com.taxi.model.domain;

public class Car {
    private Integer idCar;
    private String carNumber;
    private String brand;
    private String color;
    private String carType;

    public Car() {
    }

    public Car(Integer idCar, String carNumber, String brand, String color, String carType) {
        this.idCar = idCar;
        this.carNumber = carNumber;
        this.brand = brand;
        this.color = color;
        this.carType = carType;
    }

    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(Integer idCar) {
        this.idCar = idCar;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }


}
