package com.mytaxi.model.dao;

import com.mytaxi.model.entity.Car;

public interface CarDao extends CrudDao<Integer,Car> {
    boolean isSameCarType(Integer id_car, String carType);
}
