package my.project.model.service.impl;

import my.project.model.domain.Coupon;
import my.project.model.entity.CouponEntity;
import my.project.model.entity.OrderEntity;
import my.project.model.exception.InvalidEntityCreation;
import org.apache.log4j.Logger;
import my.project.model.dao.OrderDao;
import my.project.model.domain.Order;
import my.project.model.service.mapper.OrderMapper;
import my.project.model.service.OrderService;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);

    private final OrderDao orderDao;
    private final OrderMapper mapper;

    public OrderServiceImpl(OrderDao orderDao, OrderMapper mapper) {
        this.orderDao = orderDao;
        this.mapper = mapper;
    }

    @Override
    public boolean createOrder(Order order) {
        if (Objects.isNull(order) ) {
            LOGGER.warn("OrderEntity is not valid");
            throw new InvalidEntityCreation("OrderEntity is not valid");
        }

        return orderDao.save(mapper.mapOrderToOrderEntity(order));
    }
    @Override
    public List<Order> findAll(int currentPage, int recordsPerPage) {
        List<OrderEntity> result = orderDao.findAll(currentPage,recordsPerPage);
        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapOrderEntityToOrder)
                .collect(Collectors.toList());
    }

    @Override
    public int getNumberOfRows() {
        return orderDao.getNumberOfRows();
    }


}
