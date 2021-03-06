package com.taxi.model.entity;

import com.taxi.model.domain.Person;
import com.taxi.model.domain.enums.DriverStatus;
import com.taxi.model.domain.enums.Role;

public class DriverEntity extends Person {
    private DriverStatus driverStatus;
    private CarEntity car;
    private String middleName;

    public DriverEntity() {
        this.role = Role.DRIVER;
    }

    public DriverStatus getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(DriverStatus driverStatus) {
        this.driverStatus = driverStatus;
    }

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
}
