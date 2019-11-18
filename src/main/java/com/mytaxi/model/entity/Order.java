package com.mytaxi.model.entity;

import com.mytaxi.model.entity.enums.OrderStatus;

public class Order {
    private final Integer idOrder;
    private final OrderStatus orderStatus;
    private final Client client;
    private final Driver driver;
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

    public static Builder builder() {
        return new Builder();
    }


    public Integer getIdOrder() {
        return idOrder;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Client getClient() {
        return client;
    }

    public Driver getDriver() {
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
        private Integer idOrder;
        private OrderStatus orderStatus;
        private Client client;
        private Driver driver;
        private Address addressArrive;
        private Address addressDeparture;
        private Coupon coupon;
        private int cost;
        private int costWithDiscount;

        private Builder() {
        }

        public Builder withIdOrder(Integer idOrder) {
            this.idOrder = idOrder;
            return this;
        }

        public Builder withOrderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public Builder withClient(Client client) {
            this.client = client;
            return this;
        }

        public Builder withDriver(Driver driver) {
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
