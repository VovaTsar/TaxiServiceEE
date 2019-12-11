package com.taxi.model.mapper;

import com.taxi.model.entity.DriverEntity;
import com.taxi.model.domain.Driver;

public class DriverMapper {

   private CarMapper carMapper ;

    public DriverMapper(CarMapper carMapper) {
        this.carMapper = carMapper;
    }

    public Driver driverEntityToDriver(DriverEntity driverEntity) {
        if (driverEntity == null) {
            return null;
        }

        Driver driver = new Driver();

        driver.setPersonId(driverEntity.getPersonId());
        driver.setName(driverEntity.getName());
        driver.setSurname(driverEntity.getSurname());
        driver.setPhoneNumber(driverEntity.getPhoneNumber());
        driver.setPassword(driverEntity.getPassword());
        driver.setRole(driverEntity.getRole());
        driver.setDriverStatus(driverEntity.getDriverStatus());
        driver.setCar(carMapper.carEntityToCar(driverEntity.getCar()));
        driver.setMiddleName(driverEntity.getMiddleName());

        return driver;
    }

    public DriverEntity driverToDriverEntity(Driver driver) {
        if (driver == null) {
            return null;
        }
        DriverEntity driverEntity = new DriverEntity();

        driverEntity.setPersonId(driver.getPersonId());
        driverEntity.setName(driver.getName());
        driverEntity.setPhoneNumber(driver.getPhoneNumber());
        driverEntity.setSurname(driver.getSurname());
        driverEntity.setPassword(driver.getPassword());
        driverEntity.setDriverStatus(driver.getDriverStatus());
        driverEntity.setRole(driver.getRole());
        driverEntity.setCar(carMapper.carToCarEntity(driver.getCar()));
        driverEntity.setMiddleName(driver.getMiddleName());

        return driverEntity;
    }
}
