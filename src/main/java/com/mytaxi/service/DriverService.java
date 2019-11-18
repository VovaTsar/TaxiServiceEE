package com.mytaxi.service;

import com.mytaxi.model.dao.DriverDao;
import com.mytaxi.model.entity.Driver;
import com.mytaxi.model.entity.enums.DriverStatus;
import org.apache.log4j.Logger;

import java.util.List;

public class DriverService {
    private static final Logger LOG = Logger.getLogger(DriverService.class);
    private DriverDao dao;

    public DriverService(DriverDao dao) {
        this.dao = dao;
    }

    public boolean isDriverExists(String phoneNumber, String password){
            LOG.debug("created DriverDaoImpl");
            return dao.isDriverExist(phoneNumber, password);

    }

    public Driver getDriverByPasswordAndPhone(String phoneNumber, String password){
            LOG.debug("created DriverDaoImpl");
            return dao.getDriverByPassAndPhone(phoneNumber, password);

    }

    public Driver getDriverById(Integer id) {
            LOG.debug("created DriverDaoImpl");
            return dao.findById(id);
    }

    public List<Driver> getAllDrivers() {
            LOG.debug("created DriverDaoImpl");
            return dao.findAll();
    }

    public Driver getDriverByCarTypeAndDriverStatus(DriverStatus driverStatus, String carType) {
            LOG.debug("created DriverDaoImpl");
            return dao.getDriverByCarTypeAndDriverStatus(driverStatus, carType);
        }


    public boolean updateDriverStatus(Driver driver) {
            LOG.debug("created DriverDaoImpl");
            return dao.update(driver);

    }
}
