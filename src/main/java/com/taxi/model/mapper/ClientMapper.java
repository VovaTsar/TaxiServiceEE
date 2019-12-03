package com.taxi.model.mapper;

import com.taxi.model.domain.ClientEntity;
import com.taxi.model.entity.Client;

public class ClientMapper {
    public Client clientEntityToClient(ClientEntity clientEntity) {
        if (clientEntity == null) {
            return null;
        }
        Client client = new Client();

        client.setPersonId(clientEntity.getPersonId());
        client.setName(clientEntity.getName());
        client.setSurname(clientEntity.getSurname());
        client.setPassword(clientEntity.getPassword());
        client.setPhoneNumber(clientEntity.getPhoneNumber());
        client.setRole(clientEntity.getRole());
        client.setEmail(clientEntity.getEmail());

        return client;
    }

    public ClientEntity clientToClientEntity(Client client) {
        if (client == null) {
            return null;
        }
        ClientEntity clientEntity = new ClientEntity();

        clientEntity.setPersonId(client.getPersonId());
        clientEntity.setName(client.getName());
        clientEntity.setSurname(client.getSurname());
        clientEntity.setPassword(client.getPassword());
        clientEntity.setEmail(client.getEmail());
        clientEntity.setPhoneNumber(client.getPhoneNumber());
        clientEntity.setRole(client.getRole());

        return clientEntity;
    }
}
