package com.taxi.model.dao;

import com.taxi.model.domain.enums.OrderStatus;
import com.taxi.model.entity.OrderEntity;

import java.util.List;

public interface OrderDao extends Dao<Integer, OrderEntity> {

    List<OrderEntity> findAllOrdersByDriverId(Integer idDriver, int row, int limit);

    void createWithoutCoupon(OrderEntity order);

    long findCountOrders(Integer idDriver);

    boolean updateOrderStatus(Integer idOrder, OrderStatus orderStatus);

    boolean isCorrespondOrderAndDriver(Integer idOrder, Integer idDriver);

}
