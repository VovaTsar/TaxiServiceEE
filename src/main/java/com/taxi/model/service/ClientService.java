package com.taxi.model.service;

import com.taxi.model.domain.Client;
import com.taxi.model.exception.EmailIsAlreadyTaken;
import com.taxi.model.exception.PhoneNumberIsAlreadyTaken;

public interface ClientService {

    boolean isClientAlreadyExist(String phoneNumber, String password);

    void createClientInDatabase(Client client) throws EmailIsAlreadyTaken, PhoneNumberIsAlreadyTaken;

    Client getClientByPasswordAndPhone(String phoneNumber, String password);

}
