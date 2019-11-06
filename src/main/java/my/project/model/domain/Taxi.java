package my.project.model.domain;


import my.project.model.entity.CarType;

public class Taxi {
    private final Integer id;
    private final Address location;
    private final String carNumber;
    private final CarType carType;
    private final User driver;
    private final boolean busy;

    private Taxi(Builder builder) {
        id = builder.id;
        location = builder.location;
        carNumber = builder.carNumber;
        carType = builder.carType;
        driver = builder.driver;
        busy = builder.busy;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Integer getId() {
        return id;
    }

    public Address getLocation() {
        return location;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public CarType getCarType() {
        return carType;
    }

    public User getDriver() {
        return driver;
    }

    public boolean isBusy() {
        return busy;
    }

    public static final class Builder {
        private Integer id;
        private Address location;
        private String carNumber;
        private CarType carType;
        private User driver;
        private boolean busy;

        private Builder() {
        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withLocation(Address location) {
            this.location = location;
            return this;
        }

        public Builder withCarNumber(String carNumber) {
            this.carNumber = carNumber;
            return this;
        }

        public Builder withCarType(CarType carType) {
            this.carType = carType;
            return this;
        }

        public Builder withDriver(User driver) {
            this.driver = driver;
            return this;
        }

        public Builder withBusy(boolean busy) {
            this.busy = busy;
            return this;
        }

        public Taxi build() {
            return new Taxi(this);
        }
    }
}
