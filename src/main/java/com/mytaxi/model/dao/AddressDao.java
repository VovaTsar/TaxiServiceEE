package com.mytaxi.model.dao;

import com.mytaxi.model.entity.Address;

public interface AddressDao extends CrudDao<Integer,Address> {

    Address getAddressByStreetNumberHouse(String street, String numberHouse);
}