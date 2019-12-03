package com.taxi.model.dao;

import com.taxi.model.domain.DriverEntity;
import com.taxi.model.entity.enums.DriverStatus;

import java.util.Optional;

public interface DriverDao extends Dao<Integer, DriverEntity> {

    DriverEntity findDriverByCarTypeAndDriverStatus(DriverStatus driverStatus, String carType);

    boolean isDriverExist(String phoneNumber, String password);

    Optional<DriverEntity> findDriverByPassAndPhone(String phoneNumber, String password);

}
