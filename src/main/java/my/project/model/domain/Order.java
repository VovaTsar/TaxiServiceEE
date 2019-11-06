package my.project.model.domain;

import my.project.model.entity.OrderStatus;

public class Order {
    private final Integer id;
    private final double cost;
    private final Address startPoint;
    private final Address endPoint;
    private final User user;
    private final Taxi taxi;
    private final OrderStatus status;

    private Order(Builder builder) {
        id = builder.id;
        cost = builder.cost;
        startPoint = builder.startPoint;
        endPoint = builder.endPoint;
        user = builder.user;
        taxi = builder.taxi;
        status = builder.status;
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

    public Address getStartPoint() {
        return startPoint;
    }

    public Address getEndPoint() {
        return endPoint;
    }

    public User getUser() {
        return user;
    }

    public Taxi getTaxi() {
        return taxi;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public static final class Builder {
        private Integer id;
        private double cost;
        private Address startPoint;
        private Address endPoint;
        private User user;
        private Taxi taxi;
        private OrderStatus status;

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

        public Builder withStartPoint(Address startPoint) {
            this.startPoint = startPoint;
            return this;
        }

        public Builder withEndPoint(Address endPoint) {
            this.endPoint = endPoint;
            return this;
        }

        public Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public Builder withTaxi(Taxi taxi) {
            this.taxi = taxi;
            return this;
        }

        public Builder withStatus(OrderStatus status) {
            this.status = status;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
