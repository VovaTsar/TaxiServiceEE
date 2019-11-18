package com.mytaxi.model.dao.impl;

import com.mytaxi.model.dao.AbstractDao;
import com.mytaxi.model.dao.AddressDao;
import com.mytaxi.model.dao.connection.PoolConnection;
import com.mytaxi.model.entity.Address;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AddressDaoImpl extends AbstractDao<Address> implements AddressDao {
    private static final String SAVE_QUERY ="";

    private static final String FIND_BY_ID = "SELECT * FROM  taxi_database.adress WHERE  id_adress =(?);";

    private static final String FIND_ALL = "SELECT * FROM taxi_database.adress ;";

    private static final String UPDATE_QUERY = "";

    private static final String FIND_BY_ADDRESS = "SELECT * FROM  taxi_database.adress  WHERE street =(?) AND house_number =(?);";

    //  private static final String FIND_ADDRESS_ID = "SELECT id_adress FROM  taxi_database.adress WHERE  street =(?) AND house_number =(?);";

    private static final Logger LOGGER = Logger.getLogger(AddressDaoImpl.class);

    public AddressDaoImpl( PoolConnection connector) {
        super(SAVE_QUERY, FIND_BY_ID, FIND_ALL, UPDATE_QUERY, connector);
    }

    @Override
    public Address getAddressByStreetNumberHouse(String street, String numberHouse) {
        return getEntityByTwoStringParam(street,numberHouse,FIND_BY_ADDRESS);
    }

    @Override
    protected void updateStatementMapper(Address entity, PreparedStatement preparedStatement) throws SQLException {

    }

    @Override
    protected void createStatementMapper(Address entity, PreparedStatement preparedStatement) throws SQLException {

    }

    @Override
    protected Address mapResultSetToEntity(ResultSet entity) throws SQLException {
        return Address.builder()
                .withIdAddress(entity.getInt("id_adress"))
                .withStreet(entity.getString("street"))
                .withHouseNumber(entity.getString("house_number"))
                .build();
    }
}
