package my.project.model.entity;

public class TaxiEntity {
    private final Integer id;
    private final AddressEntity location;
    private final String carNumber;
    private final CarType carType;
    private final UserEntity driver;
    private final boolean busy;

    private TaxiEntity(Builder builder) {
        this.id = builder.id;
        this.location = builder.location;
        this.carNumber = builder.carNumber;
        this.carType = builder.carType;
        this.driver = builder.driver;
        this.busy = builder.busy;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Integer getId() {
        return id;
    }

    public AddressEntity getLocation() {
        return location;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public CarType getCarType() {
        return carType;
    }

    public UserEntity getDriver() {
        return driver;
    }

    public boolean isBusy() {
        return busy;
    }

    public static final class Builder {
        private Integer id;
        private AddressEntity location;
        private String carNumber;
        private CarType carType;
        private UserEntity driver;
        private boolean busy;

        private Builder() {
        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withLocation(AddressEntity location) {
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

        public Builder withDriver(UserEntity driver) {
            this.driver = driver;
            return this;
        }

        public Builder withBusy(boolean busy) {
            this.busy = busy;
            return this;
        }

        public TaxiEntity build() {
            return new TaxiEntity(this);
        }
    }
}
