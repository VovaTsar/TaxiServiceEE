package com.taxi.model.domain;

import com.taxi.model.domain.enums.DriverStatus;
import com.taxi.model.domain.enums.Role;

public class Driver extends Person {
    private DriverStatus driverStatus;
    private Car car;
    private String middleName;

    public Driver() {
        this.role = Role.DRIVER;
    }

    public com.taxi.model.domain.Car getCar() {
        return car;
    }

    public void setCar(com.taxi.model.domain.Car car) {
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
