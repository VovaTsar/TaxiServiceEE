package com.taxi.model.mapper;

import com.taxi.model.domain.Car;
import com.taxi.model.domain.Driver;
import com.taxi.model.domain.enums.DriverStatus;
import com.taxi.model.domain.enums.Role;
import com.taxi.model.entity.CarEntity;
import com.taxi.model.entity.DriverEntity;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DriverMapperTest {

    private static final Car CAR = getCar();

    private static final CarEntity CAR_ENTITY = getCarEntity();

    private static final DriverEntity DRIVER_ENTITY = getDriverEntity();

    private static final Driver DRIVER = getDriver();


    @Mock
    private CarMapper carMapper;

    @InjectMocks
    private DriverMapper driverMapper;

    @After
    public void resetMock() {
        reset(carMapper);
    }

    @Test
    public void shouldMapDriverEntityToDriver() {
        when(carMapper.carEntityToCar(any(CarEntity.class))).thenReturn(CAR);
        Driver actual = driverMapper.driverEntityToDriver(DRIVER_ENTITY);

        assertThat(actual.getPersonId(), is(DRIVER.getPersonId()));
        assertThat(actual.getName(), is(DRIVER.getName()));
        assertThat(actual.getSurname(), is(DRIVER.getSurname()));
        assertThat(actual.getPassword(), is(DRIVER.getPassword()));
        assertThat(actual.getPhoneNumber(), is(DRIVER.getPhoneNumber()));
        assertThat(actual.getRole(), is(DRIVER.getRole()));
        assertThat(actual.getDriverStatus(), is(DRIVER.getDriverStatus()));
        assertThat(actual.getCar(), is(DRIVER.getCar()));
        assertThat(actual.getMiddleName(), is(DRIVER.getMiddleName()));
    }

    @Test
    public void shouldMapDriverToDriverEntity() {
        when(carMapper.carToCarEntity(any(Car.class))).thenReturn(CAR_ENTITY);
        DriverEntity actual = driverMapper.driverToDriverEntity(DRIVER);

        assertThat(actual.getPersonId(), is(DRIVER_ENTITY.getPersonId()));
        assertThat(actual.getName(), is(DRIVER_ENTITY.getName()));
        assertThat(actual.getSurname(), is(DRIVER_ENTITY.getSurname()));
        assertThat(actual.getPassword(), is(DRIVER_ENTITY.getPassword()));
        assertThat(actual.getPhoneNumber(), is(DRIVER_ENTITY.getPhoneNumber()));
        assertThat(actual.getRole(), is(DRIVER_ENTITY.getRole()));
        assertThat(actual.getDriverStatus(), is(DRIVER_ENTITY.getDriverStatus()));
        assertThat(actual.getCar(), is(DRIVER_ENTITY.getCar()));
        assertThat(actual.getMiddleName(), is(DRIVER_ENTITY.getMiddleName()));
    }

    @Test
    public void mapDriverToDriverEntityShouldReturnNull() {
        DriverEntity actual = driverMapper.driverToDriverEntity(null);

        assertThat(actual, is(nullValue()));
    }

    @Test
    public void mapDriverEntityToDriverShouldReturnNull() {
        Driver actual = driverMapper.driverEntityToDriver(null);

        assertThat(actual, is(nullValue()));
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