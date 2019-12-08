package com.taxi.model.service.impl;

import com.taxi.model.dao.DriverDao;
import com.taxi.model.domain.Car;
import com.taxi.model.domain.Driver;
import com.taxi.model.domain.enums.DriverStatus;
import com.taxi.model.domain.enums.Role;
import com.taxi.model.entity.CarEntity;
import com.taxi.model.entity.DriverEntity;
import com.taxi.model.exception.InputDataUnCorrectRuntimeException;
import com.taxi.model.mapper.DriverMapper;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DriverServiceImplTest {

    private static final Car CAR = getCar();

    private static final CarEntity CAR_ENTITY = getCarEntity();

    private static final DriverEntity DRIVER_ENTITY = getDriverEntity();

    private static final Driver DRIVER = getDriver();

    private static final boolean DRIVER_EXIST = true;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private DriverDao dao;

    @Mock
    private DriverMapper mapper;

    @After
    public void resetMock() {
        reset(dao, mapper);
    }

    @InjectMocks
    private DriverServiceImpl service;

    @Test
    public void getAllAddressShouldReturnListOfAddresses() {
        when(dao.isDriverExist(anyString(), anyString())).thenReturn(DRIVER_EXIST);
        boolean actual = service.isDriverExists("+380968174473", "cerber");

        assertThat(actual, is(DRIVER_EXIST));
    }

    @Test
    public void isDriverExistsShouldThrowInputDataUnCorrectRuntimeExceptionWithEmptyParam() {
        exception.expect(InputDataUnCorrectRuntimeException.class);
        exception.expectMessage("Driver phoneNumber and password must be not null");

        service.isDriverExists("", "");
    }

    @Test
    public void getDriverByPasswordAndPhoneShouldReturnDriver() {
        when(dao.findDriverByPassAndPhone(anyString(), anyString())).thenReturn(java.util.Optional.ofNullable(DRIVER_ENTITY));
        when(mapper.driverEntityToDriver(any(DriverEntity.class))).thenReturn(DRIVER);

        Driver actual = service.getDriverByPasswordAndPhone("+380968174473", "cerber");

        assertThat(actual, is(DRIVER));
    }

    @Test
    public void getDriverByPasswordAndPhoneShouldThrowInputDataUnCorrectRuntimeExceptionWithEmptyParam() {
        exception.expect(InputDataUnCorrectRuntimeException.class);
        exception.expectMessage("Driver phoneNumber and password must be not null");

        service.isDriverExists("", "");
    }

    @Test
    public void getDriverByCarTypeAndDriverStatusShouldReturnDriver() {
        when(dao.findDriverByCarTypeAndDriverStatus(any(), anyString())).thenReturn(DRIVER_ENTITY);
        when(mapper.driverEntityToDriver(any(DriverEntity.class))).thenReturn(DRIVER);

        Driver actual = service.getDriverByCarTypeAndDriverStatus(DriverStatus.FREE, "minivan");

        assertThat(actual, is(DRIVER));
    }

    @Test
    public void getDriverByCarTypeAndDriverStatusShouldThrowInputDataUnCorrectRuntimeExceptionWithEmptyParam() {
        exception.expect(InputDataUnCorrectRuntimeException.class);
        exception.expectMessage("Driver driverStatus and password must be not null");

        service.getDriverByCarTypeAndDriverStatus(null, "");
    }

    @Test
    public void updateDriverStatusShouldUpdateDriverStatus() {
        when(mapper.driverToDriverEntity(any(Driver.class))).thenReturn(DRIVER_ENTITY);
        service.updateDriverStatus(DRIVER);

        verify(dao).update(any(DriverEntity.class));
    }

    @Test
    public void updateDriverStatusShouldThrowInputDataUnCorrectRuntimeExceptionWithEmptyParam() {
        exception.expect(InputDataUnCorrectRuntimeException.class);
        exception.expectMessage("Driver must be not null");

        service.updateDriverStatus(null);
    }

    private static Car getCar() {
        return new Car(1, "AA777AA", "Audi", "White", "minivan");

    }

    private static CarEntity getCarEntity() {
        return new CarEntity(1, "AA777AA", "Audi", "White", "minivan");

    }

    private static DriverEntity getDriverEntity() {
        DriverEntity driverEntity = new DriverEntity();

        driverEntity.setPersonId(1);
        driverEntity.setName("User");
        driverEntity.setSurname("User");
        driverEntity.setPassword("cerber");
        driverEntity.setPhoneNumber("+380968174473");
        driverEntity.setRole(Role.DRIVER);
        driverEntity.setDriverStatus(DriverStatus.FREE);
        driverEntity.setCar(CAR_ENTITY);
        driverEntity.setMiddleName("Ivanov");

        return driverEntity;
    }

    private static Driver getDriver() {
        Driver driver = new Driver();

        driver.setPersonId(1);
        driver.setName("User");
        driver.setSurname("User");
        driver.setPassword("cerber");
        driver.setPhoneNumber("+380968174473");
        driver.setRole(Role.DRIVER);
        driver.setDriverStatus(DriverStatus.FREE);
        driver.setCar(CAR);
        driver.setMiddleName("Ivanov");

        return driver;
    }
}
