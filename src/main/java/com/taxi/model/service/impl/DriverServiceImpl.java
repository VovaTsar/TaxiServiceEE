package com.taxi.model.service.impl;

import com.taxi.model.dao.DriverDao;
import com.taxi.model.domain.Driver;
import com.taxi.model.domain.enums.DriverStatus;
import com.taxi.model.entity.DriverEntity;
import com.taxi.model.exception.InputDataUnCorrectRuntimeException;
import com.taxi.model.exception.InvalidDataRuntimeException;
import com.taxi.model.mapper.DriverMapper;
import com.taxi.model.service.DriverService;
import org.apache.log4j.Logger;

import java.util.Objects;
import java.util.Optional;

public class DriverServiceImpl implements DriverService {

    private static final Logger LOG = Logger.getLogger(DriverServiceImpl.class);
    private DriverDao driverDao;
    private DriverMapper driverMapper;

    public DriverServiceImpl(DriverDao driverDao, DriverMapper driverMapper) {
        this.driverDao = driverDao;
        this.driverMapper = driverMapper;
    }

    public boolean isDriverExists(String phoneNumber, String password) {
        if (phoneNumber.isEmpty() || password.isEmpty()) {
            LOG.warn("creating DriverServiceImpl isDriverExists");
            throw new InputDataUnCorrectRuntimeException("Driver phoneNumber and password must be not null");
        }

        return driverDao.isDriverExist(phoneNumber, password);

    }

    public Driver getDriverByPasswordAndPhone(String phoneNumber, String password) {
        if (phoneNumber.isEmpty() || password.isEmpty()) {
            LOG.warn("creating DriverServiceImpl getDriverByPasswordAndPhone");
            throw new InputDataUnCorrectRuntimeException("Driver phoneNumber and password must be not null");
        }
        Optional<DriverEntity> driverEntity = driverDao.findDriverByPassAndPhone(phoneNumber, password);

        return driverEntity.map(driverMapper::driverEntityToDriver)
                .orElseThrow(() -> new InvalidDataRuntimeException("Driver  is not correct"));

    }

    public Driver getDriverByCarTypeAndDriverStatus(DriverStatus driverStatus, String carType) {
        if (Objects.isNull(driverStatus) || carType.isEmpty()) {
            LOG.warn("creating DriverServiceImpl getDriverByCarTypeAndDriverStatus");
            throw new InputDataUnCorrectRuntimeException("Driver driverStatus and password must be not null");
        }

        return driverMapper.driverEntityToDriver(driverDao.findDriverByCarTypeAndDriverStatus(driverStatus, carType));

    }

    public boolean updateDriverStatus(Driver driver) {
        if (Objects.isNull(driver)) {
            LOG.warn("creating DriverServiceImpl updateDriverStatus");
            throw new InputDataUnCorrectRuntimeException("Driver must be not null");
        }

        return driverDao.update(driverMapper.driverToDriverEntity(driver));

    }
}
