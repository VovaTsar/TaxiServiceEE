package com.taxi.model.dao.impl;

import com.taxi.model.dao.DriverDao;
import com.taxi.model.dao.connection.PoolConnection;
import com.taxi.model.entity.DriverEntity;
import com.taxi.model.domain.enums.DriverStatus;
import com.taxi.model.domain.enums.Role;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class DriverDaoImpl extends AbstractGenericDao<DriverEntity> implements DriverDao {

    private static final String FIND_DRIVER_BY_CAR_TYPE_AND_STATUS = "SELECT * FROM  driver, car  WHERE driver_status =(?) AND driver.id_car  = car.id_car  AND car_type =(?) limit 1;";

    private static final String READ_BY_PHONE_AND_PASSWORD = "SELECT * FROM  taxi_database.driver WHERE phone_number  = (?) AND password = (?);";

    private static final String READ_BY_ID = "SELECT * FROM taxi_database.driver WHERE  id_driver  =(?);";

    private static final String READ_ALL = "SELECT * FROM  taxi_database.driver ;";

    private static final String UPDATE = "UPDATE  taxi_database.driver SET  driver_status = ? WHERE id_driver  = ?;";

    private static final Logger LOG = Logger.getLogger(DriverDaoImpl.class);

    private CarDaoImpl carDaoImpl;

    public DriverDaoImpl(PoolConnection connector, CarDaoImpl carDaoImpl) {
        super(connector);
        this.carDaoImpl = carDaoImpl;
    }


    @Override
    public DriverEntity findById(Integer id) {
        return getElementByIntegerParam(id, READ_BY_ID);
    }


    @Override
    public List<DriverEntity> findAll() {
        return getList(READ_ALL);
    }


    @Override
    public DriverEntity findDriverByCarTypeAndDriverStatus(DriverStatus driverStatus, String carType) {
        DriverEntity result = null;
        try (PreparedStatement ps = connector.getConnection().prepareStatement(FIND_DRIVER_BY_CAR_TYPE_AND_STATUS)) {
            ps.setString(1, driverStatus.toString().toLowerCase());
            ps.setString(2, carType);
            final ResultSet rs = ps.executeQuery();
            LOG.debug("Executed query" + FIND_DRIVER_BY_CAR_TYPE_AND_STATUS);
            if (rs.next()) {
                LOG.debug("check is rs has next");
                result = new DriverEntity();
                result = parseToOneElement(rs);
            }
        } catch (SQLException e) {
            LOG.error("SQLException occurred in DriverDaoImpl", e);
        }
        return result;
    }


    @Override
    public boolean isDriverExist(String phoneNumber, String password) {
        return isExist(phoneNumber, password, READ_BY_PHONE_AND_PASSWORD);
    }

    @Override
    public Optional<DriverEntity> findDriverByPassAndPhone(String phoneNumber, String password) {
        return Optional.ofNullable(getElementByTwoStringParam(phoneNumber, password, READ_BY_PHONE_AND_PASSWORD));
    }

    @Override
    public boolean update(DriverEntity driver) {
        return super.update(driver, UPDATE);
    }


    @Override
    public void create(DriverEntity entity) {
        throw new UnsupportedOperationException();
    }


    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException();
    }


    @Override
    protected void setInsertElementProperties(PreparedStatement statement, DriverEntity element) throws SQLException {
        statement.setString(1, element.getDriverStatus().toString().toLowerCase());
    }

    @Override
    protected void setUpdateElementProperties(PreparedStatement statement, DriverEntity element) throws SQLException {
        setInsertElementProperties(statement, element);
        statement.setInt(2, element.getPersonId());
    }

    @Override
    protected DriverEntity parseToOneElement(ResultSet resultSet) throws SQLException {
        DriverEntity driver = new DriverEntity();

        driver.setPersonId(resultSet.getInt("id_driver"));
        driver.setSurname(resultSet.getString("surname"));
        driver.setName(resultSet.getString("name"));
        driver.setMiddleName(resultSet.getString("middle_name"));
        driver.setPassword(resultSet.getString("password"));
        driver.setPhoneNumber(resultSet.getString("phone_number"));
        driver.setDriverStatus(toDriverStatus(resultSet.getString("driver_status")));
        driver.setRole(Role.DRIVER);
        driver.setCar(carDaoImpl.findById(resultSet.getInt("id_car")));
        return driver;
    }

    private DriverStatus toDriverStatus(String status) {
        if (DriverStatus.BOOKED.toString()
                .equalsIgnoreCase(status)) {
            return DriverStatus.BOOKED;
        } else {
            return DriverStatus.FREE;
        }
    }
}
