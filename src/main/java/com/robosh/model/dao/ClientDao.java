package com.robosh.model.dao;

import com.robosh.model.entity.Client;

public interface ClientDao extends CrudDao<Integer,Client> {
    boolean isClientExists(String phoneNumber, String password);

    boolean isPhoneNumberExists(String phoneNumber);

    boolean isEmailExists(String email);

    Client getClientByPassPhone(String phoneNumber, String password);
}