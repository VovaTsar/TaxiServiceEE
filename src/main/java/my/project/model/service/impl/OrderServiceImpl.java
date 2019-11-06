package my.project.model.service.impl;

import my.project.model.exception.InvalidEntityCreation;
import org.apache.log4j.Logger;
import my.project.model.dao.OrderDao;
import my.project.model.domain.Order;
import my.project.model.service.mapper.OrderMapper;
import my.project.model.service.OrderService;

import java.util.Objects;

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



}
