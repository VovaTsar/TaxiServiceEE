package com.taxi.model.service.impl;

import com.taxi.model.dao.AddressDao;
import com.taxi.model.entity.AddressEntity;
import com.taxi.model.domain.Address;
import com.taxi.model.exception.InputDataUnCorrectRuntimeException;
import com.taxi.model.exception.InvalidDataRuntimeException;
import com.taxi.model.mapper.AddressMapper;
import com.taxi.model.service.AddressService;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AddressServiceImpl implements AddressService {

    private static final Logger LOG = Logger.getLogger(AddressServiceImpl.class);
    private AddressDao addressDao;
    private AddressMapper addressMapper;

    public AddressServiceImpl(AddressDao addressDao, AddressMapper addressMapper) {
        this.addressDao = addressDao;
        this.addressMapper = addressMapper;
    }

    public List<Address> getAllAddress() {
        List<AddressEntity> addressEntities = addressDao.findAll();

        return addressEntities.isEmpty() ?
                Collections.emptyList() : addressEntities.stream()
                .map(addressMapper::addressEntityToAddress)
                .collect(Collectors.toList());
    }


    public Address getAddressByAddressString(String addressString) {
        if (addressString.isEmpty()) {
            LOG.warn("created AddressServiceImpl getAddressByAddressString");
            throw new InputDataUnCorrectRuntimeException("Address must be not null");
        }
        int indexWhitespace = addressString.lastIndexOf(" ");
        String street = addressString.substring(0, indexWhitespace);
        String houseNumber = addressString.substring(indexWhitespace + 1);

        Optional<AddressEntity> address = addressDao.findAddressByStreetNumberHouse(street, houseNumber);

        return address.map(addressMapper::addressEntityToAddress)
                .orElseThrow(() -> new InvalidDataRuntimeException("Address is not correct"));

    }
}
