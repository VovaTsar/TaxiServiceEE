package com.taxi.model.entity;

import com.taxi.model.entity.enums.OrderStatus;

public class Order {
    private final int idOrder;
    private final OrderStatus orderStatus;
    private final User client;
    private final User driver;
    private final Address addressArrive;
    private final Address addressDeparture;
    private final Coupon coupon;
    private final int cost;
    private final int costWithDiscount;

    private Order(Builder builder) {
        this.idOrder = builder.idOrder;
        this.orderStatus = builder.orderStatus;
        this.client = builder.client;
        this.driver = builder.driver;
        this.addressArrive = builder.addressArrive;
        this.addressDeparture = builder.addressDeparture;
        this.coupon = builder.coupon;
        this.cost = builder.cost;
        this.costWithDiscount = builder.costWithDiscount;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public int getIdOrder() {
        return idOrder;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public User getClient() {
        return client;
    }

    public User getDriver() {
        return driver;
    }

    public Address getAddressArrive() {
        return addressArrive;
    }

    public Address getAddressDeparture() {
        return addressDeparture;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public int getCost() {
        return cost;
    }

    public int getCostWithDiscount() {
        return costWithDiscount;
    }

    public static final class Builder {
        private int idOrder;
        private OrderStatus orderStatus;
        private User client;
        private User driver;
        private Address addressArrive;
        private Address addressDeparture;
        private Coupon coupon;
        private int cost;
        private int costWithDiscount;

        private Builder() {
        }

        public Builder withIdOrder(int idOrder) {
            this.idOrder = idOrder;
            return this;
        }

        public Builder withOrderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public Builder withClient(User client) {
            this.client = client;
            return this;
        }

        public Builder withDriver(User driver) {
            this.driver = driver;
            return this;
        }

        public Builder withAddressArrive(Address addressArrive) {
            this.addressArrive = addressArrive;
            return this;
        }

        public Builder withAddressDeparture(Address addressDeparture) {
            this.addressDeparture = addressDeparture;
            return this;
        }

        public Builder withCoupon(Coupon coupon) {
            this.coupon = coupon;
            return this;
        }

        public Builder withCost(int cost) {
            this.cost = cost;
            return this;
        }

        public Builder withCostWithDiscount(int costWithDiscount) {
            this.costWithDiscount = costWithDiscount;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
