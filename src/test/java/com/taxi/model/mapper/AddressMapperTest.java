package com.taxi.model.mapper;

import com.taxi.model.domain.Address;
import com.taxi.model.entity.AddressEntity;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class AddressMapperTest {
    private static final AddressEntity ADDRESS_ENTITY = getAddressEntity();

    private static final Address ADDRESS = getAddress();

    private final AddressMapper addressMapper = new AddressMapper();


    @Test
    public void shouldMapAddressEntityToAddress() {
        Address actual = addressMapper.addressEntityToAddress(ADDRESS_ENTITY);

        assertThat(actual.getIdAddress(), is(ADDRESS.getIdAddress()));
        assertThat(actual.getStreet(), is(ADDRESS.getStreet()));
        assertThat(actual.getHouseNumber(), is(ADDRESS.getHouseNumber()));

    }

    @Test
    public void shouldMapAddressToAddressEntity() {
        AddressEntity actual = addressMapper.addressToAddressEntity(ADDRESS);

        assertThat(actual.getIdAddress(), is(ADDRESS_ENTITY.getIdAddress()));
        assertThat(actual.getStreet(), is(ADDRESS_ENTITY.getStreet()));
        assertThat(actual.getHouseNumber(), is(ADDRESS_ENTITY.getHouseNumber()));

    }

    @Test
    public void mapAddressToAddressEntityShouldReturnNull() {
        AddressEntity actual = addressMapper.addressToAddressEntity(null);

        assertThat(actual, is(nullValue()));
    }

    @Test
    public void mapAddressEntityToAddressShouldReturnNull() {
        Address actual = addressMapper.addressEntityToAddress(null);
        assertThat(actual, is(nullValue()));
    }

    private static AddressEntity getAddressEntity() {
        return new AddressEntity(1,"Polytech","2B");
    }

    private static Address getAddress() {
        return new Address(1,"Polytech","2B");
    }
}