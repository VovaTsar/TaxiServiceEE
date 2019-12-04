package com.taxi.model.entity;

import com.taxi.model.domain.enums.OrderStatus;

public class OrderEntity {
    private Integer idOrder;
    private OrderStatus orderStatus;
    private ClientEntity client;
    private DriverEntity driver;
    private AddressEntity addressArrive;
    private AddressEntity addressDeparture;
    private CouponEntity coupon;
    private int cost;
    private int costWithDiscount;

    public OrderEntity() {
    }

    public OrderEntity(OrderStatus orderStatus, ClientEntity client, DriverEntity driver, AddressEntity addressArrive,
                       AddressEntity addressDeparture, CouponEntity coupon, int cost, int costWithDiscount) {
        this.orderStatus = orderStatus;
        this.client = client;
        this.driver = driver;
        this.addressArrive = addressArrive;
        this.addressDeparture = addressDeparture;
        this.coupon = coupon;
        this.cost = cost;
        this.costWithDiscount = costWithDiscount;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public DriverEntity getDriver() {
        return driver;
    }

    public void setDriver(DriverEntity driver) {
        this.driver = driver;
    }

    public AddressEntity getAddressArrive() {
        return addressArrive;
    }

    public void setAddressArrive(AddressEntity addressArrive) {
        this.addressArrive = addressArrive;
    }

    public AddressEntity getAddressDeparture() {
        return addressDeparture;
    }

    public void setAddressDeparture(AddressEntity addressDeparture) {
        this.addressDeparture = addressDeparture;
    }

    public CouponEntity getCoupon() {
        return coupon;
    }

    public void setCoupon(CouponEntity coupon) {
        this.coupon = coupon;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCostWithDiscount() {
        return costWithDiscount;
    }

    public void setCostWithDiscount(int costWithDiscount) {
        this.costWithDiscount = costWithDiscount;
    }
}
