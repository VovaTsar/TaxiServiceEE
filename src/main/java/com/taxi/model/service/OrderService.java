package com.taxi.model.service;

import com.taxi.model.domain.Order;
import com.taxi.model.domain.enums.OrderStatus;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrderByIdDriver(Integer idDriver, int row, int limit);

    void createOrderWithCouponInDB(Order order);

    void createOrderWithoutCouponInDB(Order order);

    void createOrderInDB(Order order);

    long getAllOrdersCount(Integer idDriver);

    boolean updateOrderStatus(Integer idOrder, OrderStatus orderStatus);

    boolean isCorrespondOrderAndDriver(Integer idOrder, Integer idDriver);

}
