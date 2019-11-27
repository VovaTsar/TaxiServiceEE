package com.taxi.model.dao.impl;

import com.taxi.model.dao.CarDao;
import com.taxi.model.dao.connection.PoolConnection;
import com.taxi.model.entity.Car;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class CarDaoImpl extends AbstractGenericDao<Car> implements CarDao {
    private static final String READ_BY_ID = "SELECT * FROM  taxi_database.car WHERE  id_car =(?);";

    private static final String READ_BY_ID_AND_CAR_TYPE = "SELECT * FROM  taxi_database.car WHERE  id_car =(?) AND car_type =(?);";

    private static final String READ_ALL = "SELECT * FROM  taxi_database.car ;";

    private static final Logger LOG = Logger.getLogger(CarDaoImpl.class);

    public CarDaoImpl(PoolConnection connection) {
        super(connection);
    }


    @Override
    public Car findById(Integer id) {
        return getElementByIntegerParam(id, READ_BY_ID);
    }


    @Override
    public List<Car> findAll() {
        return getList(READ_ALL);
    }


    @Override
    public boolean isSameCarType(Integer idCar, String carType) {
      return isExistWithIntegerAndStringParametr(idCar,carType,READ_BY_ID_AND_CAR_TYPE);
    }

    @Override
    public void create(Car entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(Car car) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException();
    }


    @Override
    protected void setInsertElementProperties(PreparedStatement statement, Car element)  {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void setUpdateElementProperties(PreparedStatement statement, Car element)  {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Car parseToOneElement(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setIdCar(resultSet.getInt("id_car"));
        car.setCarNumber(resultSet.getString("number"));
        car.setBrand(resultSet.getString("brand"));
        car.setColor(resultSet.getString("color"));
        car.setCarType(resultSet.getString("car_type"));
        return car;
    }
}
