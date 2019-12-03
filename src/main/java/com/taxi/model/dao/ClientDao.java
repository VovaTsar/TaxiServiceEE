package com.taxi.model.dao;

import com.taxi.model.domain.ClientEntity;

import java.util.Optional;

public interface ClientDao extends Dao<Integer, ClientEntity> {

    boolean isClientExists(String phoneNumber, String password);

    boolean isPhoneNumberExists(String phoneNumber);

    boolean isEmailExists(String email);

    Optional<ClientEntity> findClientByPassPhone(String phoneNumber, String password);

}
