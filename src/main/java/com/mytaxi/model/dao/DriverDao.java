package com.mytaxi.model.dao;

import com.mytaxi.model.entity.Driver;
import com.mytaxi.model.entity.enums.DriverStatus;
public interface DriverDao extends CrudDao<Integer,Driver> {
    Driver getDriverByCarTypeAndDriverStatus(DriverStatus driverStatus, String carType);

    boolean isDriverExist(String phoneNumber, String password);

    Driver getDriverByPassAndPhone(String phoneNumber, String password);
}
