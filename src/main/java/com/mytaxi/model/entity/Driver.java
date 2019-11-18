package com.mytaxi.model.entity;

import com.mytaxi.model.entity.enums.DriverStatus;
import com.mytaxi.model.entity.enums.Role;

public class Driver extends Person {
    private DriverStatus driverStatus;
    private Car car;
    private String middleName;

    public void setDriverStatus(DriverStatus driverStatus) {
        this.driverStatus = driverStatus;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    protected Driver(DriverBuilder personPersonBuilder) {
        super(personPersonBuilder);
        this.role = Role.DRIVER;
    }
    public static DriverBuilder builder() {
        return new DriverBuilder();
    }

    public Car getCar() {
        return car;
    }
    //public void setCar(Car car) {
    //      this.car = car;
    // }

    public DriverStatus getDriverStatus() {
        return driverStatus;
    }

    //  public void setDriverStatus(DriverStatus driverStatus) {
    //       this.driverStatus = driverStatus;
    //   }

    public String getMiddleName() {
        return middleName;
    }

    //   public void setMiddleName(String middleName) {
    //      this.middleName = middleName;
    // }

    public static class DriverBuilder extends PersonBuilder<DriverBuilder> {
        private DriverStatus driverStatus;
        private Car car;
        private String middleName;

        public DriverBuilder() {
        }

        @Override
        public DriverBuilder self() {
            return this;
        }

        public Driver build() {
            return new Driver(self());
        }

        public DriverBuilder withDriverStatus(DriverStatus driverStatus) {
            this.driverStatus = driverStatus;
            return self();
        }

        public DriverBuilder withCar(Car car) {
            this.car = car;
            return self();
        }

        public DriverBuilder withMiddleName(String middleName) {
            this.middleName = middleName;
            return self();
        }
    }
}