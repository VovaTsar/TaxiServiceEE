package com.taxi.model.domain;

public class Address {
    private Integer idAddress;
    private String street;
    private String houseNumber;

    public Address() {
    }

    public Address(Integer idAddress, String street, String houseNumber) {
        this.idAddress = idAddress;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(Integer idAddress) {
        this.idAddress = idAddress;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }


}
