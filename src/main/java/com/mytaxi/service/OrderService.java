package com.mytaxi.service;

import com.mytaxi.model.dao.OrderDao;
import com.mytaxi.model.entity.*;
import com.mytaxi.model.entity.enums.OrderStatus;
import org.apache.log4j.Logger;

import java.util.List;

public class OrderService {
    private static final Logger LOG = Logger.getLogger(OrderService.class);
    private OrderDao dao;

    public OrderService(OrderDao dao) {
        this.dao = dao;
    }

    public List<Order> getAllOrderByIdDriver(Integer idDriver, int row, int limit){
            LOG.debug("created OrderDaoImpl");
            return dao.getAllOrdersByDriverId(idDriver,row, limit);
    }

    public List<Order> getAllOrders(){
            LOG.debug("created OrderDaoImpl");
            return dao.findAll();
        }
    public Order getOrderById(Integer idOrder) {
            LOG.debug("created OrderDaoImpl");
            return dao.findById(idOrder);

    }

    public void createOrderWithCouponInDB(Order order) {
            LOG.debug("created OrderDaoImpl");
            dao.save(order);

    }

    public void createOrderWithoutCouponInDB(Order order) {
            LOG.debug("created OrderDaoImpl");
            dao.createWithoutCoupon(order);

    }

    public void createOrderInDB(Order order) {
        if (order.getCoupon()==null) {
            createOrderWithoutCouponInDB(order);
        } else {
            createOrderWithCouponInDB(order);
        }

    }

    public long getAllOrdersCount(Integer idDriver){
            return dao.getCountOrders(idDriver);

    }

    public boolean updateOrderStatus(Integer idOrder, OrderStatus orderStatus){
            return dao.updateOrderStatus(idOrder, orderStatus);
    }

    public boolean isCorrespondOrderAndDriver(Integer idOrder, Integer idDriver) {
            return dao.isCorrespondOrderAndDriver(idOrder, idDriver);

    }
}
