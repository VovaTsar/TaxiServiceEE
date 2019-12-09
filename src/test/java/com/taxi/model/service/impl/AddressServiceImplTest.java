package com.taxi.model.service.impl;

import com.taxi.model.dao.AddressDao;
import com.taxi.model.domain.Address;
import com.taxi.model.entity.AddressEntity;
import com.taxi.model.exception.InputDataUnCorrectRuntimeException;
import com.taxi.model.mapper.AddressMapper;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceImplTest {

    private static final AddressEntity ADDRESS_ENTITY = getAddressEntity();

    private static final Address ADDRESS = getAddress();

    private static final List<AddressEntity> ADDRESS_ENTITIES = Arrays.asList(ADDRESS_ENTITY, ADDRESS_ENTITY);

    private static final List<Address> ADDRESSES = Arrays.asList(ADDRESS, ADDRESS);

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private AddressDao dao;

    @Mock
    private AddressMapper mapper;

    @After
    public void resetMock() {
        reset(dao, mapper);
    }

    @InjectMocks
    private AddressServiceImpl service;

    @Test
    public void getAllAddressShouldReturnListOfAddresses() {
        when(dao.findAll()).thenReturn(ADDRESS_ENTITIES);
        when(mapper.addressEntityToAddress(any(AddressEntity.class))).thenReturn(ADDRESS);

        List<Address> actual = service.getAllAddress();

        assertThat(actual, is(ADDRESSES));
    }

    @Test
    public void getAddressByAddressStringShouldThrowInputDataUnCorrectRuntimeExceptionWithEmptyParam() {
        exception.expect(InputDataUnCorrectRuntimeException.class);
        exception.expectMessage("Address must be not null");

        service.getAddressByAddressString("");
    }

    @Test
    public void getAddressByAddressStringShouldReturnAddress() {
        when(dao.findAddressByStreetNumberHouse(anyString(), anyString())).thenReturn(java.util.Optional.of(ADDRESS_ENTITY));
        when(mapper.addressEntityToAddress(any(AddressEntity.class))).thenReturn(ADDRESS);

        Address actual = service.getAddressByAddressString("Polytech 2B");
        assertThat(actual, is(ADDRESS));
    }


    private static AddressEntity getAddressEntity() {
        return new AddressEntity(1, "Polytech", "2B");
    }

    private static Address getAddress() {
        return new Address(1, "Polytech", "2B");
    }
}