package com.taxi.service;

import com.taxi.model.entity.Driver;
import com.taxi.model.entity.enums.DriverStatus;

public interface DriverService {
    boolean isDriverExists(String phoneNumber, String password);

    Driver getDriverByPasswordAndPhone(String phoneNumber, String password);
    Driver getDriverByPassword(String phoneNumber);

    Driver getDriverByCarTypeAndDriverStatus(DriverStatus driverStatus, String carType);

    boolean updateDriverStatus(Driver driver);
}
