package com.robosh.model.entity;

public class Car {
    private final Integer idCar;
    private final String carNumber;
    private final String brand;
    private final String color;
    private final String carType;

    private Car(Builder builder) {
        this.idCar = builder.idCar;
        this.carNumber = builder.carNumber;
        this.brand = builder.brand;
        this.color = builder.color;
        this.carType = builder.carType;
    }

    public static Builder builder() {
        return new Builder();
    }


    public Integer getIdCar() {
        return idCar;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public String getCarType() {
        return carType;
    }

    public static final class Builder {
        private Integer idCar;
        private String carNumber;
        private String brand;
        private String color;
        private String carType;

        private Builder() {
        }

        public Builder withIdCar(Integer idCar) {
            this.idCar = idCar;
            return this;
        }

        public Builder withCarNumber(String carNumber) {
            this.carNumber = carNumber;
            return this;
        }

        public Builder withBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder withColor(String color) {
            this.color = color;
            return this;
        }

        public Builder withCarType(String carType) {
            this.carType = carType;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}
