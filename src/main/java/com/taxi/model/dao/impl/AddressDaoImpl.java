package com.taxi.model.dao.impl;

import com.taxi.model.dao.AddressDao;
import com.taxi.model.dao.connection.PoolConnection;
import com.taxi.model.domain.AddressEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class AddressDaoImpl extends AbstractGenericDao<AddressEntity> implements AddressDao {

    private static final String READ_BY_ADDRESS = "SELECT * FROM  taxi_database.adress  WHERE street =(?) AND house_number =(?);";

    private static final String READ_BY_ID = "SELECT * FROM  taxi_database.adress WHERE  id_adress =(?);";

    private static final String READ_ALL = "SELECT * FROM taxi_database.adress ;";


    public AddressDaoImpl(PoolConnection connection) {
        super(connection);

    }

    @Override
    public Optional<AddressEntity> findAddressByStreetNumberHouse(String street, String numberHouse) {
        return Optional.ofNullable(getElementByTwoStringParam(street, numberHouse, READ_BY_ADDRESS));
    }


    @Override
    public AddressEntity findById(Integer id) {
        return getElementByIntegerParam(id, READ_BY_ID);
    }


    @Override
    public List<AddressEntity> findAll() {
        return getList(READ_ALL);
    }

    @Override
    public void create(AddressEntity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(AddressEntity address) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException();
    }


    @Override
    protected void setInsertElementProperties(PreparedStatement statement, AddressEntity element) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void setUpdateElementProperties(PreparedStatement statement, AddressEntity element) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected AddressEntity parseToOneElement(ResultSet resultSet) throws SQLException {
        AddressEntity address = new AddressEntity();
        address.setIdAddress(resultSet.getInt("id_adress"));
        address.setStreet(resultSet.getString("street"));
        address.setHouseNumber(resultSet.getString("house_number"));
        return address;
    }
}
