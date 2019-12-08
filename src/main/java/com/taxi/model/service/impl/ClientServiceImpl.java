package com.taxi.model.service.impl;

import com.taxi.model.dao.ClientDao;
import com.taxi.model.entity.ClientEntity;
import com.taxi.model.domain.Client;
import com.taxi.model.exception.EmailIsAlreadyTaken;
import com.taxi.model.exception.InputDataUnCorrectRuntimeException;
import com.taxi.model.exception.InvalidDataRuntimeException;
import com.taxi.model.exception.PhoneNumberIsAlreadyTaken;
import com.taxi.model.mapper.ClientMapper;
import com.taxi.model.service.ClientService;
import org.apache.log4j.Logger;

import java.util.Objects;
import java.util.Optional;

public class ClientServiceImpl implements ClientService {

    private static final Logger LOG = Logger.getLogger(ClientServiceImpl.class);
    private ClientDao clientDao;
    private ClientMapper clientMapper;

    public ClientServiceImpl(ClientDao clientDao, ClientMapper clientMapper) {
        this.clientDao = clientDao;
        this.clientMapper = clientMapper;
    }

    public boolean isClientAlreadyExist(String phoneNumber, String password) {
        if (phoneNumber.isEmpty() || password.isEmpty()) {
            LOG.warn("PhoneNumber or password is empty");
            throw new InvalidDataRuntimeException("PhoneNumber or password is empty");
        }

        return clientDao.isClientExists(phoneNumber, password);

    }

    public void createClientInDatabase(Client client) {
        if (Objects.isNull(client)) {
            LOG.warn("creating ClientDaoImpl createClientInDatabase");
            throw new InputDataUnCorrectRuntimeException("Client is empty");
        }
        boolean isTakenEmail = clientDao.isEmailExists(client.getEmail());
        boolean isTakenPhoneNumber = clientDao.isPhoneNumberExists(client.getPhoneNumber());
        if (isTakenEmail) {
            LOG.warn("e-mail is taken, exception occurred");
            throw new EmailIsAlreadyTaken("This email is already registered");
        }
        if (isTakenPhoneNumber) {
            LOG.warn("PhoneNumber is taken, exception occurred");
            throw new PhoneNumberIsAlreadyTaken("This phone number is already registered");
        }

        clientDao.create(clientMapper.clientToClientEntityWithoutId(client));

    }

    public Client getClientByPasswordAndPhone(String phoneNumber, String password) {
        if (phoneNumber.isEmpty() || password.isEmpty()) {
            LOG.warn("creating ClientServiceImpl getClientByPasswordAndPhone");
            throw new InputDataUnCorrectRuntimeException("Client phoneNumber and password must be not null");
        }
        Optional<ClientEntity> clientEntity = clientDao.findClientByPassPhone(phoneNumber, password);

        return clientEntity.map(clientMapper::clientEntityToClient)
                .orElseThrow(() -> new InvalidDataRuntimeException("Client is not correct "));

    }

}
