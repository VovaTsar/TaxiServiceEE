package com.taxi.model.dao.impl;

import com.taxi.model.dao.ClientDao;
import com.taxi.model.dao.connection.PoolConnection;
import com.taxi.model.entity.Client;
import com.taxi.model.entity.enums.Role;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class ClientDaoImpl extends AbstractGenericDao<Client> implements ClientDao {
    private static final String READ_BY_ID = "SELECT * FROM  taxi_database.client WHERE  id_client =(?);";

    private static final String READ_ALL = "SELECT * FROM  taxi_database.client ;";

    private static final String INSERT = "INSERT INTO  taxi_database.client ( surname,  name, phone_number,  e_mail, password ) VALUES ((?),(?),(?), (?), (?));";

    private static final String READ_BY_EMAIL = "SELECT * FROM  taxi_database.client WHERE  e_mail =(?);";

    private static final String READ_BY_PHONE_NUMBER = "SELECT * FROM  taxi_database.client WHERE  phone_number =(?);";

    private static final String READ_BY_PHONE_PASSWORD = "SELECT * FROM  taxi_database.client WHERE  phone_number  =(?) AND password =(?);";

    private static final Logger LOG = Logger.getLogger(ClientDaoImpl.class);


    public ClientDaoImpl(PoolConnection connection) {
        super(connection);

    }

    @Override
    public boolean isClientExists(String phoneNumber, String password) {
        return isExist(phoneNumber, password, READ_BY_PHONE_PASSWORD);
    }


    @Override
    public boolean isPhoneNumberExists(String phoneNumber) {
        return isExistWithOneStringParametr(phoneNumber, READ_BY_PHONE_NUMBER);
    }


    @Override
    public boolean isEmailExists(String email) {
        return isExistWithOneStringParametr(email, READ_BY_EMAIL);
    }


    @Override
    public Optional<Client> findClientByPassPhone(String phoneNumber, String password) {
        return Optional.ofNullable(getElementByTwoStringParam(phoneNumber, password, READ_BY_PHONE_PASSWORD));
    }

    @Override
    public void create(Client client) {
        insert(client, INSERT);
    }


    @Override
    public Client findById(Integer id) {
        return getElementByIntegerParam(id, READ_BY_ID);
    }


    @Override
    public List<Client> findAll() {
        return getList(READ_ALL);
    }


    @Override
    public boolean update(Client client) {
        throw new UnsupportedOperationException();
    }


    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException();
    }


    @Override
    protected void setInsertElementProperties(PreparedStatement statement, Client element) throws SQLException {
        statement.setString(1, element.getSurname());
        statement.setString(2, element.getName());
        statement.setString(3, element.getPhoneNumber());
        statement.setString(4, element.getEmail());
        statement.setString(5, element.getPassword());
    }

    @Override
    protected void setUpdateElementProperties(PreparedStatement statement, Client element)  {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Client parseToOneElement(ResultSet resultSet) throws SQLException {
        Client client = new Client();
        client.setPersonId(resultSet.getInt("id_client"));
        client.setSurname(resultSet.getString("surname"));
        client.setName(resultSet.getString("name"));
        client.setPhoneNumber(resultSet.getString("phone_number"));
        client.setEmail(resultSet.getString("e_mail"));
        client.setPassword(resultSet.getString("password"));
        client.setRole(Role.CLIENT);
        return client;
    }
}
