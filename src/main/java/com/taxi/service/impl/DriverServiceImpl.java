package com.taxi.service.impl;

import com.taxi.model.exception.InputDataUnCorrectRuntimeException;
import com.taxi.model.dao.DriverDao;
import com.taxi.model.entity.Driver;
import com.taxi.model.entity.enums.DriverStatus;
import com.taxi.service.DriverService;
import org.apache.log4j.Logger;

import java.util.Objects;

public class DriverServiceImpl implements DriverService {
    private static final Logger LOG = Logger.getLogger(DriverServiceImpl.class);
    private DriverDao driverDao;

    public DriverServiceImpl(DriverDao driverDao) {
        this.driverDao = driverDao;
    }

    public boolean isDriverExists(String phoneNumber, String password) {
        if (phoneNumber.isEmpty() || password.isEmpty()) {
            LOG.error("creating DriverServiceImpl isDriverExists");
            throw new InputDataUnCorrectRuntimeException("Driver phoneNumber and password must be not null");
        }
        LOG.debug("created DriverDaoImpl");
        return driverDao.isDriverExist(phoneNumber, password);

    }

    public Driver getDriverByPasswordAndPhone(String phoneNumber, String password) {
        if (phoneNumber.isEmpty() || password.isEmpty()) {
            LOG.error("creating DriverServiceImpl getDriverByPasswordAndPhone");
            throw new InputDataUnCorrectRuntimeException("Driver phoneNumber and password must be not null");
        }
        LOG.debug("created DriverDaoImpl");
        return driverDao.findDriverByPassAndPhone(phoneNumber, password).get();

    }

    @Override
    public Driver getDriverByPassword(String phoneNumber) {
        if (phoneNumber.isEmpty() ) {
            LOG.error("creating DriverServiceImpl getDriverByPasswordAndPhone");
            throw new InputDataUnCorrectRuntimeException("Driver phoneNumber and password must be not null");
        }
        LOG.debug("created DriverDaoImpl");
        return driverDao.findDriverByPass(phoneNumber).get();
    }


    public Driver getDriverByCarTypeAndDriverStatus(DriverStatus driverStatus, String carType) {
        if (Objects.isNull(driverStatus) || carType.isEmpty()) {
            LOG.error("creating DriverServiceImpl getDriverByCarTypeAndDriverStatus");
            throw new InputDataUnCorrectRuntimeException("Driver driverStatus and password must be not null");
        }
        LOG.debug("created DriverDaoImpl");
        return driverDao.findDriverByCarTypeAndDriverStatus(driverStatus, carType);
    }


    public boolean updateDriverStatus(Driver driver) {
        if (Objects.isNull(driver)) {
            LOG.error("creating DriverServiceImpl updateDriverStatus");
            throw new InputDataUnCorrectRuntimeException("Driver must be not null");
        }
        LOG.debug("created DriverDaoImpl");
        return driverDao.update(driver);

    }
}
