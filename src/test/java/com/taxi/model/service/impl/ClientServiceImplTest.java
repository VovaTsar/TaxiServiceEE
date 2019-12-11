package com.taxi.model.service.impl;

import com.taxi.model.dao.ClientDao;
import com.taxi.model.domain.Client;
import com.taxi.model.domain.enums.Role;
import com.taxi.model.entity.ClientEntity;
import com.taxi.model.exception.EmailIsAlreadyTaken;
import com.taxi.model.exception.InputDataUnCorrectRuntimeException;
import com.taxi.model.exception.InvalidDataRuntimeException;
import com.taxi.model.exception.PhoneNumberIsAlreadyTaken;
import com.taxi.model.mapper.ClientMapper;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {

    private static final ClientEntity CLIENT_ENTITY = getClientEntity();

    private static final Client CLIENT = getClient();

    private static final boolean CLIENT_EXIST = true;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private ClientDao dao;

    @Mock
    private ClientMapper mapper;

    @After
    public void resetMock() {
        reset(dao, mapper);
    }

    @InjectMocks
    private ClientServiceImpl service;

    @Test
    public void isClientAlreadyExistShouldReturnTrueWithExistClient() {
        when(dao.isClientExists(anyString(), anyString())).thenReturn(CLIENT_EXIST);

        boolean actual = service.isClientAlreadyExist("+380968174473", "cerber");

        assertThat(actual, is(CLIENT_EXIST));
    }

    @Test
    public void isClientAlreadyExistShouldThrowInvalidDataRuntimeExceptionWithEmptyParams() {
        exception.expect(InvalidDataRuntimeException.class);
        exception.expectMessage("PhoneNumber or password is empty");

        service.isClientAlreadyExist("", "");
    }

    @Test
    public void createClientInDatabaseShouldCreateClient() {
        when(dao.isEmailExists(anyString())).thenReturn(false);
        when(dao.isPhoneNumberExists(anyString())).thenReturn(false);
        when(mapper.clientToClientEntity(any(Client.class))).thenReturn(CLIENT_ENTITY);

        service.createClientInDatabase(CLIENT);

        verify(dao).create(any(ClientEntity.class));
    }

    @Test
    public void createClientInDatabaseShouldThrowEmailIsAlreadyTaken() {
        when(dao.isEmailExists(anyString())).thenReturn(true);
        when(dao.isPhoneNumberExists(anyString())).thenReturn(false);
        exception.expect(EmailIsAlreadyTaken.class);
        exception.expectMessage("This email is already registered");

        service.createClientInDatabase(CLIENT);
    }

    @Test
    public void createClientInDatabaseShouldThrowPhoneNumberIsAlreadyTaken() {
        when(dao.isEmailExists(anyString())).thenReturn(false);
        when(dao.isPhoneNumberExists(anyString())).thenReturn(true);
        exception.expect(PhoneNumberIsAlreadyTaken.class);
        exception.expectMessage("This phone number is already registered");

        service.createClientInDatabase(CLIENT);
    }

    @Test
    public void createClientInDatabaseShouldThrowInvalidDataRuntimeExceptionWithEmptyParams() {
        exception.expect(InputDataUnCorrectRuntimeException.class);
        exception.expectMessage("Client is empty");

        service.createClientInDatabase(null);
    }

    @Test
    public void getClientByPasswordAndPhoneShouldThrowInputDataUnCorrectRuntimeExceptionWithEmptyParams() {
        exception.expect(InputDataUnCorrectRuntimeException.class);
        exception.expectMessage("Client phoneNumber and password must be not null");

        service.getClientByPasswordAndPhone("","");
    }
    @Test
    public void getClientByPasswordAndPhoneShouldReturnClient() {
       when(dao.findClientByPassPhone(anyString(),anyString())).thenReturn(java.util.Optional.ofNullable(CLIENT_ENTITY));
       when(mapper.clientEntityToClient(any(ClientEntity.class))).thenReturn(CLIENT);

        Client actual = service.getClientByPasswordAndPhone("+380968174473", "cerber");

        assertThat(actual,is(CLIENT));
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
