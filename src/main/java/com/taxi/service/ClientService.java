package com.taxi.service;

import com.taxi.model.exception.EmailIsAlreadyTaken;
import com.taxi.model.exception.PhoneNumberIsAlreadyTaken;
import com.taxi.model.entity.Client;

public interface ClientService {
    boolean isClientAlreadyExist(String phoneNumber, String password);

    void createClientInDatabase(Client client) throws EmailIsAlreadyTaken, PhoneNumberIsAlreadyTaken;

    Client getClientByPasswordAndPhone(String phoneNumber, String password);
}
