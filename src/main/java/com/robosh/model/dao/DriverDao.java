package com.robosh.model.dao;

import com.robosh.model.entity.Driver;
import com.robosh.model.entity.enums.DriverStatus;
public interface DriverDao extends CrudDao<Integer,Driver> {
    Driver getDriverByCarTypeAndDriverStatus(DriverStatus driverStatus, String carType);

    boolean isDriverExist(String phoneNumber, String password);

    Driver getDriverByPassAndPhone(String phoneNumber, String password);
}
