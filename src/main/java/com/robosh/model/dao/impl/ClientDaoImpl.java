package com.robosh.model.dao.impl;

import com.robosh.model.dao.AbstractDao;
import com.robosh.model.dao.ClientDao;
import com.robosh.model.dao.connection.PoolConnection;
import com.robosh.model.entity.Client;
import com.robosh.model.entity.enums.Role;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class ClientDaoImpl extends AbstractDao<Client> implements ClientDao {

    private static final String UPDATE_QUERY = "";
    private static final String FIND_BY_ID = "SELECT * FROM  taxi_database.client WHERE  id_client =(?);";

    private static final String FIND_ALL = "SELECT * FROM  taxi_database.client ;";

    private static final String SAVE_QUERY = "INSERT INTO  taxi_database.client ( surname,  name, phone_number,  e_mail, password ) VALUES ((?),(?),(?), (?), (?));";

    private static final String FIND_BY_EMAIL = "SELECT * FROM  taxi_database.client WHERE  e_mail =(?);";

    private static final String FIND_BY_PHONE_NUMBER = "SELECT * FROM  taxi_database.client WHERE  phone_number =(?);";

    private static final String FIND_BY_PHONE_PASSWORD = "SELECT * FROM  taxi_database.client WHERE  phone_number  =(?) AND password =(?);";

    private static final Logger LOG = Logger.getLogger(ClientDaoImpl.class);

    public ClientDaoImpl(PoolConnection connector) {
        super(SAVE_QUERY, FIND_BY_ID, FIND_ALL, UPDATE_QUERY, connector);
    }

    @Override
    public boolean isClientExists(String phoneNumber, String password) {
        return  isEntityExistByTwoStringParam(phoneNumber,password,FIND_BY_PHONE_PASSWORD);
    }


    @Override
    public boolean isPhoneNumberExists(String phoneNumber) {
        return isEntityExistByStringParam(phoneNumber, FIND_BY_PHONE_NUMBER);
    }

    @Override
    public boolean isEmailExists(String email) {
        return isEntityExistByStringParam(email, FIND_BY_EMAIL);
    }

    @Override
    public Client getClientByPassPhone(String phoneNumber, String password) {
        return getEntityByTwoStringParam(phoneNumber, password, FIND_BY_PHONE_PASSWORD);
    }

    @Override
    protected void updateStatementMapper(Client entity, PreparedStatement preparedStatement) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void createStatementMapper(Client entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getSurname());
        preparedStatement.setString(2, entity.getName());
        preparedStatement.setString(3, entity.getPhoneNumber());
        preparedStatement.setString(4, entity.getEmail());
        preparedStatement.setString(5, entity.getPassword());
    }

    @Override
    protected Client mapResultSetToEntity(ResultSet entity) throws SQLException {
        return Client.builder()
                .withPersonId(entity.getInt("id_client"))
                .withSurname(entity.getString("surname"))
                .withName(entity.getString("name"))
                .withPhoneNumber(entity.getString("phone_number"))
                .withEmail(entity.getString("e_mail"))
                .withPassword(entity.getString("password"))
                .withRole(Role.CLIENT)
                .build();
    }
}
