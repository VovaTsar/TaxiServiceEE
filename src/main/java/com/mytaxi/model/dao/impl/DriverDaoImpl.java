package com.mytaxi.model.dao.impl;

import com.mytaxi.model.dao.AbstractDao;
import com.mytaxi.model.dao.DriverDao;
import com.mytaxi.model.dao.connection.PoolConnection;
import com.mytaxi.model.entity.Car;
import com.mytaxi.model.entity.Driver;
import com.mytaxi.model.entity.enums.DriverStatus;
import com.mytaxi.model.entity.enums.Role;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverDaoImpl extends AbstractDao<Driver> implements DriverDao {
    private static final String SAVE_QUERY ="";
    private static final String FIND_DRIVER_BY_CAR_TYPE_AND_STATUS = "SELECT * FROM  driver, car  WHERE driver_status =(?) AND driver.id_car  = car.id_car  AND car_type =(?) limit 1;";

    private static final String CHECK_STATUS = "SELECT * FROM  taxi_database.driver WHERE  driver_status =(?);";

    private static final String FIND_BY_PHONE_AND_PASSWORD = "SELECT * FROM  taxi_database.driver WHERE phone_number  = (?) AND password = (?);";

    private static final String FIND_BY_ID = "SELECT * FROM taxi_database.driver WHERE  id_driver  =(?);";

    private static final String FIND_ALL = "SELECT * FROM  taxi_database.driver ;";

    private static final String UPDATE_QUERY = "UPDATE  taxi_database.driver SET  driver_status =(?) WHERE id_driver  = (?);";

    private static final Logger LOG = Logger.getLogger(DriverDaoImpl.class);

    public DriverDaoImpl( PoolConnection connector) {
        super(SAVE_QUERY, FIND_BY_ID, FIND_ALL, UPDATE_QUERY, connector);
    }


    @Override
    public Driver getDriverByCarTypeAndDriverStatus(DriverStatus driverStatus, String carType) {
        return getEntityByTwoStringParam(driverStatus.toString(),carType,FIND_DRIVER_BY_CAR_TYPE_AND_STATUS);
    }

    @Override
    public boolean isDriverExist(String phoneNumber, String password) {
        return isEntityExistByTwoStringParam(phoneNumber,password,FIND_BY_PHONE_AND_PASSWORD);
    }

    @Override
    public Driver getDriverByPassAndPhone(String phoneNumber, String password) {
        return getEntityByTwoStringParam(phoneNumber,password,FIND_BY_PHONE_AND_PASSWORD);
    }

    @Override
    protected void updateStatementMapper(Driver entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1,entity.getDriverStatus().toString().toLowerCase());
        //  createStatementMapper(entity,preparedStatement);
        preparedStatement.setInt(2,entity.getPersonId());
    }

    @Override
    protected void createStatementMapper(Driver entity, PreparedStatement preparedStatement) throws SQLException {
//        preparedStatement.setString(1,entity.getName());
//        preparedStatement.setString(2,entity.getSurname());
//        preparedStatement.setString(3,entity.getMiddleName());
//        preparedStatement.setString(4,entity.getPassword());
//        preparedStatement.setString(5,entity.getPhoneNumber());
//        preparedStatement.setString(6,entity.getDriverStatus().toString());
//        preparedStatement.setString(7,entity.getRole().toString());
//        preparedStatement.setInt(8,entity.getCar().getIdCar());
    }

    @Override
    protected Driver mapResultSetToEntity(ResultSet entity) throws SQLException {

        Car car=Car.builder()
                .withIdCar(entity.getInt("id_car"))
                .build();

        String sts = entity.getString("driver_status");
        DriverStatus status = sts.equalsIgnoreCase("BOOKED") ? DriverStatus.BOOKED :  DriverStatus.FREE;

        return Driver.builder()
                .withPersonId(entity.getInt("id_driver"))
                .withSurname(entity.getString("surname"))
                .withName(entity.getString("name"))
                .withMiddleName(entity.getString("middle_name"))
                .withPassword(entity.getString("password"))
                .withPhoneNumber(entity.getString("phone_number"))
                .withDriverStatus(status)
                .withRole(Role.DRIVER)
                .withCar(car)
                .build();


    }
}
