package com.taxi.model.dao;

import com.taxi.model.entity.AddressEntity;

import java.util.Optional;

public interface AddressDao extends Dao<Integer, AddressEntity> {

    Optional<AddressEntity> findAddressByStreetNumberHouse(String street, String numberHouse);

}
