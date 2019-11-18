package com.mytaxi.model.dao.impl;

import com.mytaxi.model.dao.AbstractDao;
import com.mytaxi.model.dao.CarDao;
import com.mytaxi.model.dao.connection.PoolConnection;
import com.mytaxi.model.entity.Car;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CarDaoImpl extends AbstractDao<Car> implements CarDao {
    private static final String SAVE_QUERY ="";
    private static final String UPDATE_QUERY = "";
    private static final String  FIND_BY_ID = "SELECT * FROM  taxi_database.car WHERE  id_car =(?);";

    private static final String  FIND_BY_ID_AND_CAR_TYPE = "SELECT * FROM  taxi_database.car WHERE  id_car =(?) AND car_type =(?);";

    // private static final String  FIND_BY_TYPE = "SELECT * FROM  taxi_database.car WHERE car_type =(?);";

    private static final String FIND_ALL = "SELECT * FROM  taxi_database.car ;";
    private static final Logger LOG = Logger.getLogger(CarDaoImpl.class);

    public CarDaoImpl( PoolConnection connector) {
        super(SAVE_QUERY, FIND_BY_ID, FIND_ALL, UPDATE_QUERY, connector);
    }

    @Override
    public boolean isSameCarType(Integer idCar, String carType) {
        return isExistWithIntegerAndStringParametr(idCar,carType,FIND_BY_ID_AND_CAR_TYPE);
    }

    @Override
    protected void updateStatementMapper(Car entity, PreparedStatement preparedStatement) throws SQLException {

    }

    @Override
    protected void createStatementMapper(Car entity, PreparedStatement preparedStatement) throws SQLException {

    }

    @Override
    protected Car mapResultSetToEntity(ResultSet entity) throws SQLException {
        return Car.builder()
                .withIdCar(entity.getInt("id_car"))
                .withCarNumber(entity.getString("number"))
                .withBrand(entity.getString("brand"))
                .withColor(entity.getString("color"))
                .withCarType(entity.getString("car_type"))
                .build();
    }
}