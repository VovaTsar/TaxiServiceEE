package my.project.model.entity;

public class AddressEntity {
    private final Integer id;
    private final String street;
    private final int house;
    private final int x;
    private final int y;

    private AddressEntity(Builder builder) {
        this.id = builder.id;
        this.street = builder.street;
        this.house = builder.house;
        this.x = builder.x;
        this.y = builder.y;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static final class Builder {
        private Integer id;
        private String street;
        private int house;
        private int x;
        private int y;

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

        public Builder withX(int x) {
            this.x = x;
            return this;
        }

        public Builder withY(int y) {
            this.y = y;
            return this;
        }

        public AddressEntity build() {
            return new AddressEntity(this);
        }
    }
}
