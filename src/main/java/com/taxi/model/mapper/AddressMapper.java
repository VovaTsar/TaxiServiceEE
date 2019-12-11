package com.taxi.model.mapper;

import com.taxi.model.domain.Address;
import com.taxi.model.entity.AddressEntity;

public class AddressMapper {

    public Address addressEntityToAddress(AddressEntity addressEntity) {
        if (addressEntity == null) {
            return null;
        }

        Address address = new Address();
        address.setIdAddress(addressEntity.getIdAddress());
        address.setStreet(addressEntity.getStreet());
        address.setHouseNumber(addressEntity.getHouseNumber());

        return address;
    }

    public AddressEntity addressToAddressEntity(Address address) {
        if (address == null) {
            return null;
        }
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setIdAddress(address.getIdAddress());
        addressEntity.setStreet(address.getStreet());
        addressEntity.setHouseNumber(address.getHouseNumber());
        return addressEntity;
    }
}
