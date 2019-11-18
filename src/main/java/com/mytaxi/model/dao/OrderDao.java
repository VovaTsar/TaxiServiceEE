package com.mytaxi.model.dao;

import com.mytaxi.model.entity.Order;
import com.mytaxi.model.entity.enums.OrderStatus;

import java.util.List;

public interface OrderDao extends CrudDao<Integer,Order> {
    List<Order> getAllOrdersByDriverId(Integer idDriver, int row, int limit);

    void createWithoutCoupon(Order order);

    long getCountOrders(Integer idDriver);

    boolean updateOrderStatus(Integer idOrder, OrderStatus orderStatus);

    boolean isCorrespondOrderAndDriver(Integer idOrder, Integer idDriver);
}
