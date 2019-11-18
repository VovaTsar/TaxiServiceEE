package com.robosh.model.dao;

import com.robosh.model.entity.Address;

public interface AddressDao extends CrudDao<Integer,Address> {

    Address getAddressByStreetNumberHouse(String street, String numberHouse);
}