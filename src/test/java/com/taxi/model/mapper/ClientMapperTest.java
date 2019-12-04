package com.taxi.model.mapper;

import com.taxi.model.domain.Client;
import com.taxi.model.domain.enums.Role;
import com.taxi.model.entity.ClientEntity;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class ClientMapperTest {
    private static final ClientEntity CLIENT_ENTITY = getClientEntity();

    private static final Client CLIENT = getClient();

    private final ClientMapper clientMapper = new ClientMapper();


    @Test
    public void shouldMapClientEntityToClient() {
        Client actual = clientMapper.clientEntityToClient(CLIENT_ENTITY);

        assertThat(actual.getPersonId(), is(CLIENT.getPersonId()));
        assertThat(actual.getName(), is(CLIENT.getName()));
        assertThat(actual.getSurname(), is(CLIENT.getSurname()));
        assertThat(actual.getPassword(), is(CLIENT.getPassword()));
        assertThat(actual.getPhoneNumber(), is(CLIENT.getPhoneNumber()));
        assertThat(actual.getRole(), is(CLIENT.getRole()));
        assertThat(actual.getEmail(), is(CLIENT.getEmail()));
    }

    @Test
    public void shouldMapClientToClientEntity() {
        ClientEntity actual = clientMapper.clientToClientEntity(CLIENT);

        assertThat(actual.getPersonId(), is(CLIENT_ENTITY.getPersonId()));
        assertThat(actual.getName(), is(CLIENT_ENTITY.getName()));
        assertThat(actual.getSurname(), is(CLIENT_ENTITY.getSurname()));
        assertThat(actual.getPassword(), is(CLIENT_ENTITY.getPassword()));
        assertThat(actual.getPhoneNumber(), is(CLIENT_ENTITY.getPhoneNumber()));
        assertThat(actual.getRole(), is(CLIENT_ENTITY.getRole()));
        assertThat(actual.getEmail(), is(CLIENT_ENTITY.getEmail()));

    }

    @Test
    public void shouldMapClientToClientEntityWithoutId() {
        ClientEntity actual = clientMapper.clientToClientEntityWithoutId(CLIENT);

        assertThat(actual.getName(), is(CLIENT_ENTITY.getName()));
        assertThat(actual.getSurname(), is(CLIENT_ENTITY.getSurname()));
        assertThat(actual.getPassword(), is(CLIENT_ENTITY.getPassword()));
        assertThat(actual.getPhoneNumber(), is(CLIENT_ENTITY.getPhoneNumber()));
        assertThat(actual.getRole(), is(CLIENT_ENTITY.getRole()));
        assertThat(actual.getEmail(), is(CLIENT_ENTITY.getEmail()));

    }

    @Test
    public void mapClientToClientEntityShouldReturnNull() {
        ClientEntity actual = clientMapper.clientToClientEntity(null);

        assertThat(actual, is(nullValue()));
    }

    @Test
    public void mapClientEntityToClientShouldReturnNull() {
        Client actual = clientMapper.clientEntityToClient(null);
        assertThat(actual, is(nullValue()));
    }

    @Test
    public void mapClientToClientEntityWithoutIdShouldReturnNull() {
        ClientEntity actual = clientMapper.clientToClientEntityWithoutId(null);
        assertThat(actual, is(nullValue()));
    }

    private static ClientEntity getClientEntity() {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setPersonId(1);
        clientEntity.setName("User");
        clientEntity.setSurname("User");
        clientEntity.setPassword("cerber");
        clientEntity.setPhoneNumber("+380968174473");
        clientEntity.setRole(Role.CLIENT);
        clientEntity.setEmail("user@gmail.com");

        return clientEntity;
    }

    private static Client getClient() {
        Client client = new Client();
        client.setPersonId(1);
        client.setName("User");
        client.setSurname("User");
        client.setPassword("cerber");
        client.setPhoneNumber("+380968174473");
        client.setRole(Role.CLIENT);
        client.setEmail("user@gmail.com");
        return client;
    }

}