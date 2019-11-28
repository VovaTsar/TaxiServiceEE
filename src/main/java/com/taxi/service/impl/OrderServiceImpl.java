package com.taxi.service.impl;

import com.taxi.model.exception.InputDataUnCorrectRuntimeException;
import com.taxi.model.dao.OrderDao;
import com.taxi.model.entity.*;
import com.taxi.model.entity.enums.OrderStatus;
import com.taxi.service.OrderService;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Objects;

public class OrderServiceImpl implements OrderService {
    private static final Logger LOG = Logger.getLogger(OrderServiceImpl.class);
    private OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public List<Order> getAllOrderByIdDriver(Integer idDriver, int row, int limit){
            LOG.debug("created OrderDaoImpl");
            return orderDao.findAllOrdersByDriverId(idDriver,row, limit);
    }


    public void createOrderWithCouponInDB(Order order) {
        if (Objects.isNull(order)) {
            LOG.error("creating OrderServiceImpl createOrderWithCouponInDB");
            throw new InputDataUnCorrectRuntimeException("Order must be not null");
        }
            LOG.debug("created OrderDaoImpl createOrderWithCouponInDB");
        orderDao.create(order);

    }

    public void createOrderWithoutCouponInDB(Order order) {
        if (Objects.isNull(order)) {
            LOG.error("creating OrderServiceImpl createOrderWithCouponInDB");
            throw new InputDataUnCorrectRuntimeException("Order must be not null");
        }
            LOG.debug("created OrderDaoImpl");
        orderDao.createWithoutCoupon(order);

    }

    public void createOrderInDB(Client client, Driver driver, Address addressDeparture,
                                Address addressArrive, Coupon coupon, int costs, int costsWithDiscount){
        Order order = new Order(OrderStatus.EXECUTING, client, driver, addressDeparture,
                addressArrive, coupon, costs, costsWithDiscount);

        if (Objects.isNull(order.getCoupon())){
            createOrderWithoutCouponInDB(order);
        }else {
            createOrderWithCouponInDB(order);
        }

    }

    public long getAllOrdersCount(Integer idDriver){
        if (idDriver<0) {
            LOG.error("creating OrderServiceImpl getAllOrdersCount");
            throw new InputDataUnCorrectRuntimeException("Id driver must be not null");
        }
            return orderDao.findCountOrders(idDriver);

    }

    public boolean updateOrderStatus(Integer idOrder, OrderStatus orderStatus){
        if (idOrder<0 ||Objects.isNull(orderStatus)) {
            LOG.error("creating OrderServiceImpl updateOrderStatus");
            throw new InputDataUnCorrectRuntimeException("Id order and orderStatus must be not null");
        }
            return orderDao.updateOrderStatus(idOrder, orderStatus);
    }

    public boolean isCorrespondOrderAndDriver(Integer idOrder, Integer idDriver) {
        if (idOrder<0 ||idDriver<0) {
            LOG.error("creating OrderServiceImpl isCorrespondOrderAndDriver");
            throw new InputDataUnCorrectRuntimeException("Id order and Id driver must be not null");
        }
            return orderDao.isCorrespondOrderAndDriver(idOrder, idDriver);

    }
}
