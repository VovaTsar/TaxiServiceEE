package my.project.model.service;


import my.project.model.domain.Order;

import java.util.List;

public interface OrderService {

    boolean createOrder(Order order);

    List<Order> findAll(int currentPage, int recordsPerPage);

    int getNumberOfRows();

}
