package my.project.model.domain;


import my.project.model.entity.CarType;

public class Taxi {
    private final Integer id;
    private final String carNumber;
    private final CarType carType;
    private final User driver;
    private final boolean busy;

    private Taxi(Builder builder) {
        this.id = builder.id;
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
