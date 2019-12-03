package com.taxi.model.mapper;

import com.taxi.model.domain.OrderEntity;
import com.taxi.model.entity.Order;

public class OrderMapper {

    private ClientMapper clientMapper;
    private DriverMapper driverMapper;
    private AddressMapper addressMapper;
    private CouponMapper couponMapper;

    public OrderMapper(ClientMapper clientMapper, DriverMapper driverMapper,
                       AddressMapper addressMapper, CouponMapper couponMapper) {
        this.clientMapper = clientMapper;
        this.driverMapper = driverMapper;
        this.addressMapper = addressMapper;
        this.couponMapper = couponMapper;
    }

    public Order orderEntityToOrder(OrderEntity orderEntity) {
        if (orderEntity == null) {
            return null;
        }

        Order order = new Order();

        order.setIdOrder(orderEntity.getIdOrder());
        order.setOrderStatus(orderEntity.getOrderStatus());
        order.setClient(clientMapper.clientEntityToClient(orderEntity.getClient()));
        order.setDriver(driverMapper.driverEntityToDriver(orderEntity.getDriver()));
        order.setAddressArrive(addressMapper.addressEntityToAddress(orderEntity.getAddressArrive()));
        order.setAddressDeparture(addressMapper.addressEntityToAddress(orderEntity.getAddressDeparture()));
        order.setCoupon(couponMapper.couponEntityToCoupon(orderEntity.getCoupon()));
        order.setCost(orderEntity.getCost());
        order.setCostWithDiscount(orderEntity.getCostWithDiscount());


        return order;
    }

    public OrderEntity orderToOrderEntity(Order order) {
        if (order == null) {
            return null;
        }
        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setOrderStatus(order.getOrderStatus());
        orderEntity.setDriver(driverMapper.driverToDriverEntity(order.getDriver()));
        orderEntity.setClient(clientMapper.clientToClientEntity(order.getClient()));
        orderEntity.setAddressArrive(addressMapper.addressToAddressEntity(order.getAddressArrive()));
        orderEntity.setAddressDeparture(addressMapper.addressToAddressEntity(order.getAddressDeparture()));
        orderEntity.setCost(order.getCost());
        orderEntity.setCoupon(couponMapper.couponToCouponEntity(order.getCoupon()));
        orderEntity.setCostWithDiscount(order.getCostWithDiscount());

        return orderEntity;

    }
}
