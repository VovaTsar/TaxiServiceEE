package com.taxi.service.impl;

import com.taxi.model.exception.InputDataUncorrectRuntimeExeption;
import com.taxi.model.dao.AddressDao;
import com.taxi.model.entity.Address;
import com.taxi.service.AddressService;
import org.apache.log4j.Logger;

import java.util.List;

public class AddressServiceImpl implements AddressService {
    private static final Logger LOG = Logger.getLogger(AddressServiceImpl.class);
    private AddressDao addressDao;


    public AddressServiceImpl(AddressDao addressDao) {
        this.addressDao = addressDao;
    }


    public List<Address> getAllAddress() {
        LOG.debug("created AddressServiceImpl getAllAddress");
        return addressDao.findAll();
    }


    public Address getAddressByAddressString(String addressString) {
        if (addressString.isEmpty()){
            LOG.error("created AddressServiceImpl getAddressByAddressString");
            throw  new InputDataUncorrectRuntimeExeption("Address must be not null");
        }
        int indexWhitespace = addressString.lastIndexOf(" ");
        String street = addressString.substring(0, indexWhitespace);
        String houseNumber = addressString.substring(indexWhitespace + 1);
        LOG.debug("created AddressServiceImpl getAddressByAddressString");
        return addressDao.findAddressByStreetNumberHouse(street, houseNumber).get();

    }
}
