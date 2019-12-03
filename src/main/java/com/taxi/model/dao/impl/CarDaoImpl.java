package com.taxi.model.dao.impl;

import com.taxi.model.dao.CarDao;
import com.taxi.model.dao.connection.PoolConnection;
import com.taxi.model.domain.CarEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class CarDaoImpl extends AbstractGenericDao<CarEntity> implements CarDao {

    private static final String READ_BY_ID = "SELECT * FROM  taxi_database.car WHERE  id_car =(?);";

    private static final String READ_ALL = "SELECT * FROM  taxi_database.car ;";


    public CarDaoImpl(PoolConnection connection) {
        super(connection);
    }


    @Override
    public CarEntity findById(Integer id) {
        return getElementByIntegerParam(id, READ_BY_ID);
    }


    @Override
    public List<CarEntity> findAll() {
        return getList(READ_ALL);
    }

    @Override
    public void create(CarEntity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(CarEntity car) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException();
    }


    @Override
    protected void setInsertElementProperties(PreparedStatement statement, CarEntity element) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void setUpdateElementProperties(PreparedStatement statement, CarEntity element) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected CarEntity parseToOneElement(ResultSet resultSet) throws SQLException {
        CarEntity car = new CarEntity();
        car.setIdCar(resultSet.getInt("id_car"));
        car.setCarNumber(resultSet.getString("number"));
        car.setBrand(resultSet.getString("brand"));
        car.setColor(resultSet.getString("color"));
        car.setCarType(resultSet.getString("car_type"));
        return car;
    }
}
