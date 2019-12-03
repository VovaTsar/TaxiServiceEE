package com.taxi.model.service;

import com.taxi.model.entity.Address;

import java.util.List;

public interface AddressService {

    List<Address> getAllAddress();

    Address getAddressByAddressString(String addressString);

}
