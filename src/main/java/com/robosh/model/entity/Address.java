package com.robosh.model.entity;

import java.util.Objects;

public class Address {
    private final Integer idAddress;
    private  final String street;
    private final String houseNumber;

    private Address(Builder builder) {
        idAddress = builder.idAddress;
        street = builder.street;
        houseNumber = builder.houseNumber;
    }

    public static Builder builder() {
        return new Builder();
    }


    public Integer getIdAddress() {
        return idAddress;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public static final class Builder {
        private Integer idAddress;
        private String street;
        private String houseNumber;

        private Builder() {
        }

        public Builder withIdAddress(Integer idAddress) {
            this.idAddress = idAddress;
            return this;
        }

        public Builder withStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder withHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
}
