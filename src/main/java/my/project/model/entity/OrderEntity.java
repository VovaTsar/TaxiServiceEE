package my.project.model.entity;

public class OrderEntity {
    private final Integer id;
    private final double cost;
    private final AddressEntity startPoint;
    private final AddressEntity endPoint;
    private final OrderStatus status;
    private final UserEntity userEntity;
    private final TaxiEntity taxiEntity;

    private OrderEntity(Builder builder) {
        this.id = builder.id;
        this.cost = builder.cost;
        this.startPoint = builder.startPoint;
        this.endPoint = builder.endPoint;
        this.status = builder.status;
        this.userEntity = builder.userEntity;
        this.taxiEntity = builder.taxiEntity;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Integer getId() {
        return id;
    }

    public double getCost() {
        return cost;
    }

    public AddressEntity getStartPoint() {
        return startPoint;
    }

    public AddressEntity getEndPoint() {
        return endPoint;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public TaxiEntity getTaxiEntity() {
        return taxiEntity;
    }

    public static final class Builder {
        private Integer id;
        private double cost;
        private AddressEntity startPoint;
        private AddressEntity endPoint;
        private OrderStatus status;
        private UserEntity userEntity;
        private TaxiEntity taxiEntity;

        private Builder() {
        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withCost(double cost) {
            this.cost = cost;
            return this;
        }

        public Builder withStartPoint(AddressEntity startPoint) {
            this.startPoint = startPoint;
            return this;
        }

        public Builder withEndPoint(AddressEntity endPoint) {
            this.endPoint = endPoint;
            return this;
        }

        public Builder withStatus(OrderStatus status) {
            this.status = status;
            return this;
        }

        public Builder withUser(UserEntity userEntity) {
            this.userEntity = userEntity;
            return this;
        }

        public Builder withTaxi(TaxiEntity taxiEntity) {
            this.taxiEntity = taxiEntity;
            return this;
        }

        public OrderEntity build() {
            return new OrderEntity(this);
        }
    }
}
