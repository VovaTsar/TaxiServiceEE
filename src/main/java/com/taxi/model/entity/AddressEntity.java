package com.taxi.model.entity;

public class AddressEntity {
    private Integer idAddress;
    private String street;
    private String houseNumber;

    public AddressEntity() {
    }

    public AddressEntity(Integer idAddress, String street, String houseNumber) {
        this.idAddress = idAddress;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public Integer getIdAddress() {
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
