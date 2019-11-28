package com.taxi.model.dao;

import com.taxi.model.entity.Order;
import com.taxi.model.entity.enums.OrderStatus;

import java.util.List;

public interface OrderDao extends Dao<Integer, Order> {

    List<Order> findAllOrdersByDriverId(Integer idDriver, int row, int limit);

    void createWithoutCoupon(Order order);

    long findCountOrders(Integer idDriver);

    boolean updateOrderStatus(Integer idOrder, OrderStatus orderStatus);

    boolean isCorrespondOrderAndDriver(Integer idOrder, Integer idDriver);

}
