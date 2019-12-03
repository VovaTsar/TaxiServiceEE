package com.taxi.model.service.impl;

import com.taxi.model.dao.OrderDao;
import com.taxi.model.domain.OrderEntity;
import com.taxi.model.entity.Order;
import com.taxi.model.entity.enums.OrderStatus;
import com.taxi.model.exception.InputDataUnCorrectRuntimeException;
import com.taxi.model.mapper.OrderMapper;
import com.taxi.model.service.OrderService;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    private static final Logger LOG = Logger.getLogger(OrderServiceImpl.class);
    private OrderDao orderDao;
    private OrderMapper orderMapper;

    public OrderServiceImpl(OrderDao orderDao, OrderMapper orderMapper) {
        this.orderDao = orderDao;
        this.orderMapper = orderMapper;
    }

    public List<Order> getAllOrderByIdDriver(Integer idDriver, int row, int limit) {
        if (idDriver < 0 || row < 0 || limit < 0) {
            LOG.warn("OrderServiceImpl getAllOrderByIdDriver");
            throw new InputDataUnCorrectRuntimeException("idDriver, row,limit must be positive");
        }
        List<OrderEntity> orderEntities = orderDao.findAllOrdersByDriverId(idDriver, row, limit);

        return orderEntities.isEmpty() ?
                Collections.emptyList() : orderEntities.stream()
                .map(orderMapper::orderEntityToOrder)
                .collect(Collectors.toList());
    }


    public void createOrderWithCouponInDB(Order order) {
        if (Objects.isNull(order)) {
            LOG.warn("creating OrderServiceImpl createOrderWithCouponInDB");
            throw new InputDataUnCorrectRuntimeException("Order must be not null");
        }

        orderDao.create(orderMapper.orderToOrderEntity(order));

    }

    public void createOrderWithoutCouponInDB(Order order) {
        if (Objects.isNull(order)) {
            LOG.warn("creating OrderServiceImpl createOrderWithCouponInDB");
            throw new InputDataUnCorrectRuntimeException("Order must be not null");
        }

        orderDao.createWithoutCoupon(orderMapper.orderToOrderEntity(order));

    }

    public void createOrderInDB(Order order) {
        if (Objects.isNull(order.getCoupon())) {
            createOrderWithoutCouponInDB(order);
        } else {
            createOrderWithCouponInDB(order);
        }

    }

    public long getAllOrdersCount(Integer idDriver) {
        if (idDriver < 0) {
            LOG.warn("creating OrderServiceImpl getAllOrdersCount");
            throw new InputDataUnCorrectRuntimeException("Id driver must be not null");
        }
        return orderDao.findCountOrders(idDriver);

    }

    public boolean updateOrderStatus(Integer idOrder, OrderStatus orderStatus) {
        if (idOrder < 0 || Objects.isNull(orderStatus)) {
            LOG.error("creating OrderServiceImpl updateOrderStatus");
            throw new InputDataUnCorrectRuntimeException("Id order and orderStatus must be not null");
        }

        return orderDao.updateOrderStatus(idOrder, orderStatus);

    }

    public boolean isCorrespondOrderAndDriver(Integer idOrder, Integer idDriver) {
        if (idOrder < 0 || idDriver < 0) {
            LOG.error("creating OrderServiceImpl isCorrespondOrderAndDriver");
            throw new InputDataUnCorrectRuntimeException("Id order and Id driver must be not null");
        }

        return orderDao.isCorrespondOrderAndDriver(idOrder, idDriver);

    }
}
