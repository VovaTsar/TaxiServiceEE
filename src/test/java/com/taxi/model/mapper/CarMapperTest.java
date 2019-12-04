package com.taxi.model.mapper;

import com.taxi.model.domain.Car;
import com.taxi.model.entity.CarEntity;
import org.junit.Test;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class CarMapperTest {
    private static final CarEntity CAR_ENTITY = getCarEntity();

    private static final Car CAR = getCar();

    private final CarMapper carMapper = new CarMapper();


    @Test
    public void shouldMapCarEntityToCar() {
        Car actual = carMapper.carEntityToCar(CAR_ENTITY);

        assertThat(actual.getIdCar(), is(CAR.getIdCar()));
        assertThat(actual.getCarNumber(), is(CAR.getCarNumber()));
        assertThat(actual.getBrand(), is(CAR.getBrand()));
        assertThat(actual.getColor(), is(CAR.getColor()));
        assertThat(actual.getCarType(), is(CAR.getCarType()));
    }

    @Test
    public void shouldMapCarToCarEntity() {
        CarEntity actual = carMapper.carToCarEntity(CAR);

        assertThat(actual.getIdCar(), is(CAR_ENTITY.getIdCar()));
        assertThat(actual.getCarNumber(), is(CAR_ENTITY.getCarNumber()));
        assertThat(actual.getBrand(), is(CAR_ENTITY.getBrand()));
        assertThat(actual.getColor(), is(CAR_ENTITY.getColor()));
        assertThat(actual.getCarType(), is(CAR_ENTITY.getCarType()));

    }

    @Test
    public void mapCarToCarEntityShouldReturnNull() {
        CarEntity actual = carMapper.carToCarEntity(null);

        assertThat(actual, is(nullValue()));
    }

    @Test
    public void mapCarEntityToCarShouldReturnNull() {
        Car actual = carMapper.carEntityToCar(null);
        assertThat(actual, is(nullValue()));
    }

    private static CarEntity getCarEntity() {
        return new CarEntity(1,"AA777AA","Audi","White","minivan");
    }

    private static Car getCar() {
        return new Car(1,"AA777AA","Audi","White","minivan");
    }


}