package com.mytaxi.service;

import com.mytaxi.model.dao.AddressDao;
import com.mytaxi.model.entity.Address;
import org.apache.log4j.Logger;

import java.util.List;

public class AddressService {
    private static final Logger LOG = Logger.getLogger(AddressService.class);
    private AddressDao dao;


    public AddressService(AddressDao dao) {
        this.dao = dao;
    }



    public Address getAddressById(int id) {
        LOG.debug("created Address DAO");
        return dao.findById(id);

    }

    public List<Address> getAllAddress() {
        LOG.debug("created Address DAO");
        return dao.findAll();
    }


    public Address getAdressByAdressString(String addressString) {
        int indexWhitespace = addressString.lastIndexOf(" ");
        String street = addressString.substring(0, indexWhitespace);
        String houseNumber = addressString.substring(indexWhitespace + 1);
        LOG.debug("created Address DAO");
        return dao.getAddressByStreetNumberHouse(street, houseNumber);

    }
}
