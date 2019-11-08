package my.project.model.entity;

public class AddressEntity {
    private final Integer id;
    private final String street;
    private final int house;
    private final int coordinateX;
    private final int coordinateY;

    private AddressEntity(Builder builder) {
        this.id = builder.id;
        this.street = builder.street;
        this.house = builder.house;
        this.coordinateX = builder.coordinateX;
        this.coordinateY = builder.coordinateY;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Integer getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public int getHouse() {
        return house;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public static final class Builder {
        private Integer id;
        private String street;
        private int house;
        private int coordinateX;
        private int coordinateY;

        private Builder() {
        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder withHouse(int house) {
            this.house = house;
            return this;
        }

        public Builder withCoordinateX(int coordinateX) {
            this.coordinateX = coordinateX;
            return this;
        }

        public Builder withCoordinateY(int coordinateY) {
            this.coordinateY = coordinateY;
            return this;
        }

        public AddressEntity build() {
            return new AddressEntity(this);
        }
    }
}
