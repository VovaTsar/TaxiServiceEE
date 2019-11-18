package com.robosh.service;

import com.robosh.model.customExceptions.EmailIsAlreadyTaken;
import com.robosh.model.customExceptions.PhoneNumberIsAlreadyTaken;
import com.robosh.model.dao.ClientDao;
import com.robosh.model.entity.Client;
import org.apache.log4j.Logger;

import java.util.List;

public class ClientService {
    private static final Logger LOG = Logger.getLogger(ClientService.class);
    private  ClientDao dao;

    public ClientService(ClientDao dao) {
        this.dao = dao;
    }

    public boolean isClientAlreadyExist(String phoneNumber, String password){
            LOG.debug("created ClientDaoImpl");
            return dao.isClientExists(phoneNumber, password);

    }

    public Client getClientById(Integer id){
            LOG.debug("created ClientDaoImpl");
            return dao.findById(id);
    }

    public boolean isPhoneNumberAlreadyExists(String phoneNumber){
            LOG.debug("created ClientDaoImpl");
            return dao.isPhoneNumberExists(phoneNumber);
    }

    public boolean isEmailAlreadyExists(String email){
            LOG.debug("created ClientDaoImpl");
            return dao.isEmailExists(email);

    }

    public void createClientInDatabase(Client client) throws EmailIsAlreadyTaken, PhoneNumberIsAlreadyTaken {
            LOG.debug("created ClientDaoImpl");
            boolean isTakenEmail = dao.isEmailExists(client.getEmail());
            boolean isTakenPhoneNumber = dao.isPhoneNumberExists(client.getPhoneNumber());
            if (isTakenEmail){
                LOG.debug("e-mail is taken, exception occurred");
                throw new EmailIsAlreadyTaken("This email is already registered");
            }
            if (isTakenPhoneNumber){
                LOG.debug("PhoneNumber is taken, exception occurred");
                throw new PhoneNumberIsAlreadyTaken("This phone number is already registered");
            }
            dao.save(client);

    }

    public Client getClientByPasswordAndPhone(String phoneNumber, String password){
            LOG.debug("created ClientDaoImpl");
            return dao.getClientByPassPhone(phoneNumber, password);

    }

    public List<Client> getAllClients(){
            LOG.debug("created ClientDaoImpl");
            return dao.findAll();

    }
}
