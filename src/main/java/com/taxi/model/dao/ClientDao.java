package com.taxi.model.dao;

import com.taxi.model.entity.Client;

import java.util.Optional;

public interface ClientDao extends Dao<Integer,Client> {
    boolean isClientExists(String phoneNumber, String password);

    boolean isPhoneNumberExists(String phoneNumber);

    boolean isEmailExists(String email);

    Optional<Client> findClientByPassPhone(String phoneNumber, String password);
}
