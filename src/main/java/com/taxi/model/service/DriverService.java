package com.taxi.model.service;

import com.taxi.model.domain.Driver;
import com.taxi.model.domain.enums.DriverStatus;

public interface DriverService {

    boolean isDriverExists(String phoneNumber, String password);

    Driver getDriverByPasswordAndPhone(String phoneNumber, String password);

    Driver getDriverByCarTypeAndDriverStatus(DriverStatus driverStatus, String carType);

    boolean updateDriverStatus(Driver driver);

}
