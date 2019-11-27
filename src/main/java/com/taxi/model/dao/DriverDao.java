package com.taxi.model.dao;

import com.taxi.model.entity.Driver;
import com.taxi.model.entity.enums.DriverStatus;

import java.util.Optional;

public interface DriverDao extends Dao<Integer,Driver> {
   Driver findDriverByCarTypeAndDriverStatus(DriverStatus driverStatus, String carType);

    boolean isDriverExist(String phoneNumber, String password);

    Optional<Driver> findDriverByPassAndPhone(String phoneNumber, String password);
    Optional<Driver> findDriverByPass(String phoneNumber);
}
