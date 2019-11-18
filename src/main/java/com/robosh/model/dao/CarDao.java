package com.robosh.model.dao;

import com.robosh.model.entity.Car;

public interface CarDao extends CrudDao<Integer,Car> {
    boolean isSameCarType(Integer id_car, String carType);
}
