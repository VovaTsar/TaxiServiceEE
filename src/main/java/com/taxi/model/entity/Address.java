package com.taxi.model.entity;

import java.util.Objects;

public class Address {
    private final Long idAddress;
    private final String street;
    private final String houseNumber;

    public Address(Long idAddress, String street, String houseNumber) {
        this.idAddress = idAddress;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public Long getIdAddress() {
        return idAddress;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        return idAddress == address.idAddress &&
                Objects.equals(street, address.street) &&
                Objects.equals(houseNumber, address.houseNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAddress, street, houseNumber);
    }

    @Override
    public String toString() {
        return "Address{" +
                "idAddress=" + idAddress +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                '}';
    }
}
