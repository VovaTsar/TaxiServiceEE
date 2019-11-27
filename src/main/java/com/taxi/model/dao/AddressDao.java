package com.taxi.model.dao;

import com.taxi.model.entity.Address;

import java.util.Optional;

public interface AddressDao extends Dao<Integer,Address> {

    Optional<Address> findAddressByStreetNumberHouse(String street, String numberHouse);
}
