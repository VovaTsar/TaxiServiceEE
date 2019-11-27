package com.taxi.service;

import com.taxi.model.entity.*;
import com.taxi.model.entity.enums.OrderStatus;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrderByIdDriver(Integer idDriver, int row, int limit);

    void createOrderWithCouponInDB(Order order);

    void createOrderWithoutCouponInDB(Order order);

    void createOrderInDB(Client client, Driver driver, Address addressDeparture,
                         Address addressArrive, Coupon coupon, int costs, int costsWithDiscount);

    long getAllOrdersCount(Integer idDriver);

    boolean updateOrderStatus(Integer idOrder, OrderStatus orderStatus);

    boolean isCorrespondOrderAndDriver(Integer idOrder, Integer idDriver);
}
