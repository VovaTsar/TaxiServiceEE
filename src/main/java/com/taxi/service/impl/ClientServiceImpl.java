package com.taxi.service.impl;

import com.taxi.model.exception.EmailIsAlreadyTaken;
import com.taxi.model.exception.InputDataUnCorrectRuntimeException;
import com.taxi.model.exception.PhoneNumberIsAlreadyTaken;
import com.taxi.model.dao.ClientDao;
import com.taxi.model.entity.Client;
import com.taxi.service.ClientService;
import org.apache.log4j.Logger;

import java.util.Objects;

public class ClientServiceImpl implements ClientService {
    private static final Logger LOG = Logger.getLogger(ClientServiceImpl.class);
    private ClientDao clientDao;

    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public boolean isClientAlreadyExist(String phoneNumber, String password) {
        LOG.debug("created ClientDaoImpl");

        return clientDao.isClientExists(phoneNumber, password);

    }

    public void createClientInDatabase(Client client) throws EmailIsAlreadyTaken, PhoneNumberIsAlreadyTaken {
        if (Objects.isNull(client)){
            LOG.error("creating ClientDaoImpl createClientInDatabase");
            throw  new InputDataUnCorrectRuntimeException("Client is empty");
        }
        LOG.debug("created ClientDaoImpl");
        boolean isTakenEmail = clientDao.isEmailExists(client.getEmail());
        boolean isTakenPhoneNumber = clientDao.isPhoneNumberExists(client.getPhoneNumber());
        if (isTakenEmail) {
            LOG.debug("e-mail is taken, exception occurred");
            throw new EmailIsAlreadyTaken("This email is already registered");
        }
        if (isTakenPhoneNumber) {
            LOG.debug("PhoneNumber is taken, exception occurred");
            throw new PhoneNumberIsAlreadyTaken("This phone number is already registered");
        }
        clientDao.create(client);

    }

    public Client getClientByPasswordAndPhone(String phoneNumber, String password) {
        if (phoneNumber.isEmpty()||password.isEmpty()){
            LOG.error("creating ClientServiceImpl getClientByPasswordAndPhone");
            throw new InputDataUnCorrectRuntimeException("Client phoneNumber and password must be not null");
        }
        LOG.debug("created ClientDaoImpl");
        return clientDao.findClientByPassPhone(phoneNumber, password).get();

    }

}
