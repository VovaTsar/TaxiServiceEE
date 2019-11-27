package com.taxi.model.dao;

import com.taxi.model.entity.Car;

public interface CarDao extends Dao<Integer,Car> {
    boolean isSameCarType(Integer idCar, String carType);
}
