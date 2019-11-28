package com.taxi.model.entity;

import com.taxi.model.entity.enums.DriverStatus;
import com.taxi.model.entity.enums.Role;

public class Driver extends Person {
    private DriverStatus driverStatus;
    private Car car;
    private String middleName;

    public Driver() {
        this.role = Role.DRIVER;
    }

    public com.taxi.model.entity.Car getCar() {
        return car;
    }

    public void setCar(com.taxi.model.entity.Car car) {
        this.car = car;
    }

    public DriverStatus getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(DriverStatus driverStatus) {
        this.driverStatus = driverStatus;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

}
